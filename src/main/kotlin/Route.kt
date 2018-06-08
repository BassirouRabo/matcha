import Campus.FREMONT
import Campus.PARIS
import Gender.FEMALE
import Gender.MALE
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion
import data.User
import data.UserData
import data.Users
import data.Chats
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.content.MultiPartData
import io.ktor.content.PartData
import io.ktor.content.forEachPart
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import io.ktor.locations.get
import io.ktor.locations.location
import io.ktor.locations.locations
import io.ktor.locations.post
import io.ktor.network.util.ioCoroutineDispatcher
import io.ktor.response.respondRedirect
import io.ktor.response.respondWrite
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import io.ktor.websocket.webSocket
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.mapNotNull
import kotlinx.coroutines.experimental.withContext
import kotlinx.coroutines.experimental.yield
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import pages.*
import repository.UserRepository.toUserdate
import java.awt.Frame
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import io.ktor.http.cio.websocket.Frame.Text
import io.ktor.request.*
import org.apache.commons.codec.digest.DigestUtils
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import repository.*
import java.text.SimpleDateFormat
import java.util.*

fun Routing.homeRoute() {
    location<HomeUrl> {
        get {
            var user : User? = null
            val username : String? = call.sessions.get<Session>()?.username
            var friends : List<User>
            var scores : Int = 0
            if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
            else {
                var users: List<User> = listOf()
                transaction {
                    users = UserRepository.getAll().also { it.forEach { scores += it.score } }
                    user = UserRepository.getByUsername(username)
                }
                if (user!!.score == 0) call.respondRedirect(application.locations.href(InfoUrl(username)))
                else {
                    // Block - is Activate, orientations, is _report
                    friends = getFriends(username).filter { transaction { BloqueRepository.get(username, it.username) == null && it.isReport == false && it.isActivate } }
                    users = users.filter { it.username != username }.filter {  transaction { BloqueRepository.get(username, it.username) == null } && it.orientation == user!!.orientation && !it.isReport && it.isActivate  && it.username != user!!.username }
                            .sortedBy { it.campus }
                    call.homePage(user!!, users, friends, scores)
                }

            }
        }
        post{
            var user : User? = null
            var users: List<User> = listOf()
            val username : String? = call.sessions.get<Session>()?.username
            val params = call.receiveParameters()
            if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
            else {
                transaction { user = UserRepository.getByUsername(username) }
                if (user == null)  call.respondRedirect(application.locations.href(LoginUrl()))
                else {
                    val gender = if (params[Users.gender.name]!! == Gender.MALE.toString()) Gender.MALE else Gender.FEMALE
                    val orientation = if (params[Users.orientation.name]!! == Orientation.BI.toString()) Orientation.BI else Orientation.HO
                    val campus = if (params[Users.campus.name]!! == Campus.PARIS.toString()) Campus.PARIS else Campus.FREMONT

                    val range = params["range"]!!.split(",")
                    var scores : Int = 0
                    transaction {  UserRepository.getAll().forEach { scores += it.score }  }

                    transaction { users = UserRepository.getAll(Users.isActivate.eq(true) and Users.isReport.eq(false) and Users.gender.eq(gender) and Users.orientation.eq(orientation) and Users.campus.eq(campus) ) }

                    users = users.filter { transaction { BloqueRepository.get(username, it.username) == null  } }.filter { it.username != username && it.age >= range[0].toInt() && it.age <= range[1].toInt() }

                    params["tagBio"]?.let { users.filter { it.tagBio } }
                    params["tagGeek"]?.let { users.filter { it.tagGeek } }
                    params["tagSmart"]?.let { users.filter { it.tagSmart } }
                    params["tagPiercing"]?.let { users.filter { it.tagPiercing } }
                    params["tagShy"]?.let { users.filter { it.tagShy } }

                    call.homePage(user!!, users, getFriends(username), scores)
                }
            }
        }
    }
}

fun Routing.loginRoute() {
    location<LoginUrl> {
        get {
            call.loginPage()
        }
        post {
            var user: User? = null
            val params = call.receiveParameters()

            val username: String? = params[Users.username.name]
            val password: String? = params[Users.password.name]

            if (username == null || password == null) call.respondRedirect(application.locations.href(LoginUrl()))
            transaction {
                user = UserRepository.getByUsernameAndPassword(username!!, password!!)
            }
            if (user == null) call.respondRedirect(application.locations.href(LoginUrl()))
            else {
                if (user!!.isActivate) {
                    call.sessions.set(Session(username = user!!.username))
                    call.respondRedirect(application.locations.href(HomeUrl()))
                } else call.respondRedirect(application.locations.href(ActivateUrl(username!!)))
            }
        }
    }
}

fun Routing.logoutRoute() {
    location<LogoutUrl> {
        get {

            val username = call.sessions.get<Session>()?.username?.let {
                transaction {
                    UserRepository.getByUsername(it)?.let {
                        Chat.memberLeft(it)
                    }
                }
            }
            call.sessions.clear<Session>()
            call.respondRedirect(application.locations.href(HomeUrl()))
        }
    }
}

fun Routing.registerRoute() {
    get<RegisterUrl> {
        call.registerPage()
    }

    post<RegisterUrl> {
        val params = call.receiveParameters()

        val username: String? = params[Users.username.name]
        val email: String? = params[Users.email.name]
        val firstName: String? = params[Users.firstName.name]
        val lastName: String? = params[Users.lastName.name]
        val age: Int? = params[Users.age.name]?.toInt()
        val password: String? = params[Users.password.name]
        val photo: String? = params[Users.photo.name]
        val gender: String? = params[Users.gender.name]
        val campus: String? = params[Users.campus.name]

        val random = Random().nextInt(9999 - 1000) + 1000


        if (username.equals(null)
                || email.equals(null)
                || firstName.equals(null)
                || lastName.equals(null)
                || age == 0
                || password.equals(null)
                || gender.equals(null)
                || campus.equals(null)) {
            call.respondRedirect(application.locations.href(RegisterUrl()))
        } else {
            transaction {
                val user: User? = UserRepository.getByUsername(username!!)
                if (user == null) UserRepository.add(UserData(username = username, email = email!!, firstName = firstName!!, lastName = lastName!!, age = age!!, date = DateTime.now(), password = password!!, biography = "My biography", photo = photo
                        ?: "default", isActivate = false, code = random, gender = if (gender.equals("Male")) MALE else FEMALE, campus = if (campus.equals("Paris")) PARIS else FREMONT))?.let {
                    Mail.sendMail(it.email, "Your activation code is : ${it.code}")
                }
            }
            call.respondRedirect(application.locations.href(LoginUrl()))
        }
    }
}

fun Routing.activateRoute() {

    get<ActivateUrl> { activateUrl ->
        call.activatePage(activateUrl.username)
    }

    post<ActivateUrl> { activateUrl ->
        var user: User? = null
        val params = call.receiveParameters()
        val username = call.parameters[Users.username.name]
        val code = params[Users.code.name]
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else if (code == null) call.respondRedirect(application.locations.href(ActivateUrl(username)))
        else {
            transaction {
                user = UserRepository.get(Users.username.eq(username) and Users.code.eq(code.toInt()))
            }
            if (user == null) {
                call.respondRedirect(application.locations.href(ActivateUrl(username)))
            } else {
                transaction {
                    user!!.isActivate = true
                    user = UserRepository.update(username, toUserdate(user!!))
                }
                if (user == null || !user!!.isActivate) {
                    call.respondRedirect(application.locations.href(ActivateUrl(username)))
                } else call.respondRedirect(application.locations.href(HomeUrl()))
            }
        }
    }
}

fun Routing.userRoute() {
    get<UserUrl> { userUrl ->
        var user: User? = null
        var currentUser : User? = null
        val username = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {
                user = UserRepository.getByUsername(userUrl.username)
                currentUser = UserRepository.getByUsername(username)

            }
            if (user == null || currentUser == null) call.respondRedirect(application.locations.href(HomeUrl()))
            else {
                if (user!!.username == username) {
                    val likes: MutableList<User> = mutableListOf<User>()
                    val likeds: MutableList<User> = mutableListOf<User>()
                    val visits: MutableList<User> = mutableListOf<User>()
                    val visiteds: MutableList<User> = mutableListOf<User>()
                    val bloques: MutableList<User> = mutableListOf<User>()
                    var chats : List<User> = listOf()
                    transaction {
                        VisitRepository.getVisits(username).forEach { UserRepository.getByUsername(it.username2)?.let { visits.add(it) } }
                        VisitRepository.getVisiteds(username).forEach { UserRepository.getByUsername(it.username1)?.let { visiteds.add(it) } }
                        LikeRepository.getLikes(username).forEach { UserRepository.getByUsername(it.username2)?.let { likes.add(it) } }
                        LikeRepository.getLikeds(username).forEach { UserRepository.getByUsername(it.username1)?.let { likes.add(it) } }
                        BloqueRepository.getBloques(username).forEach { UserRepository.getByUsername(it.username2)?.let { bloques.add(it) } }
                        chats = ChatRepository.getChats(username)
                    }
                    call.profilPage(user!!, likes, likeds, visits, visiteds, bloques, chats)
                } else {
                    transaction { VisitRepository.add(username, user!!.username) }
                    val photo = if (user!!.photo == "default") "public/photos/35x35.png" else "/public/photos/${user!!.photo}"
                    Chat.sendMessage(username1 = username, username2 = user!!.username, type = MSG_VISIT, message = "visits you", photo = photo)
                    call.userPage(user!!, currentUser!!)
                }
            }
        }
    }

    post<UserUrl> { userUrl ->
        userUrl.username
    }
}

fun Routing.chatRoute() {
    get<ChatUrl> { chatUrl ->
        var user1 : User? = null
        var user2 : User? = null
        var chats : List<data.Chat> = listOf()
        val username : String? = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            if (username != chatUrl.username1)  call.respondRedirect(application.locations.href(UserUrl(username)))
            else {
                transaction {
                    user1 = UserRepository.getByUsername(chatUrl.username1)
                    user2 = UserRepository.getByUsername(chatUrl.username2)

                }
                if (user1 == null || user2 == null) call.respondRedirect(application.locations.href(LoginUrl()))
                else {
                    transaction {
                        chats = ChatRepository.getAll(username1 = user1!!.username, username2 = user2!!.username)
                    }
                    call.chatPage(user1!!, user2!!, chats)
                }
            }
        }
    }
}

fun Routing.likeRoute() {
    get<LikeUrl> { likeUrl ->
        val username = call.sessions.get<Session>()?.username
        var user : User? = null
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {user = UserRepository.getByUsername(username) }

            if (user == null) call.respondRedirect(application.locations.href(LoginUrl()))
            else {
                transaction {
                    LikeRepository.like(username, likeUrl.username)
                }

                val photo = if (user!!.photo == "default") "public/photos/35x35.png" else "/public/photos/${user!!.photo}"
                Chat.sendMessage(username1 = username, username2 = likeUrl.username, type = MSG_LIKE, message = "likes you", photo = photo)
                call.respondRedirect(application.locations.href(UserUrl(likeUrl.username)))

            }
        }
    }
}

fun Routing.unlikeRoute() {
    get<UnLikeUrl> { unlikeUrl ->
        val username = call.sessions.get<Session>()?.username
        var user : User? = null
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {user = UserRepository.getByUsername(username) }

            if (user == null) call.respondRedirect(application.locations.href(LoginUrl()))
            else {
                transaction {
                    LikeRepository.unlike(username, unlikeUrl.username)
                }
                val photo = if (user!!.photo == "default") "public/photos/35x35.png" else "/public/photos/${user!!.photo}"
                Chat.sendMessage(username1 = username, username2 = unlikeUrl.username, type = MSG_UNLIKE, message = "unlikes you", photo = photo)
                call.respondRedirect(application.locations.href(UserUrl(unlikeUrl.username)))
            }
        }
    }
}

fun Routing.infoRoute() {

    get<InfoUrl> { infoUrl ->
        var user : User? = null
        val username : String? = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {
                user = UserRepository.getByUsername(username)
            }
            if (user == null) call.respondRedirect(application.locations.href(LoginUrl())) else call.infoPage(user!!)
        }
    }

    post<InfoUrl> { infoUrl ->
        var user : User? = null
        val params = call.receiveParameters()
        transaction {
            user = UserRepository.getByUsername(infoUrl.username)
        }
        if (user == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {
                user!!.orientation = if (params[Users.orientation.name].equals(Orientation.BI.toString())) Orientation.BI else Orientation.HO
                user!!.tagBio = params[Users.tagBio.name] != null
                user!!.tagGeek = params[Users.tagGeek.name] != null
                user!!.tagPiercing = params[Users.tagPiercing.name] != null
                user!!.tagSmart = params[Users.tagSmart.name] != null
                user!!.tagShy = params[Users.tagShy.name] != null
                user!!.score = 1
            }
            call.respondRedirect(application.locations.href(HomeUrl()))
        }
    }


}

fun Routing.photoRoute() {
    post<PhotoUrl> { photoUrl ->
        var user : User? = null
        val multipart = call.receiveMultipart()
        val uploadDir = File(PHOTO_SRC)
        transaction {
            user = UserRepository.getByUsername(photoUrl.username)
        }
        if (user == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        transaction {
                            when (part.name) {
                                Users.username.name -> user!!.firstName = part.value
                                Users.lastName.name -> user!!.lastName = part.value
                                Users.email.name -> {
                                    val value = part.value
                                    if (user!!.email != part.value) Mail.sendMail(user!!.email, "Your Email has been changed to $value")
                                    user!!.email = value
                                }
                                Users.age.name -> user!!.age = part.value.toInt()
                                Users.biography.name -> user!!.biography = part.value
                                Users.password.name -> {
                                    val pass =  DigestUtils.md5Hex(part.value).toUpperCase()
                                    user!!.password = pass
                                }
                            }
                        }
                    }
                    is PartData.FileItem ->
                        if (!part.originalFileName.isNullOrEmpty()) {
                            val ext = File(part.originalFileName).extension
                            val file = File(uploadDir, "${user!!.username}-${System.currentTimeMillis()}.$ext")
                            part.streamProvider().use { its -> file.outputStream().buffered().use { its.copyToSuspend(it) } }
                            transaction {
                                when (part.name) {
                                    Users.photo.name -> user!!.photo = file.name
                                    Users.photo1.name -> user!!.photo1 = file.name
                                    Users.photo2.name -> user!!.photo2 = file.name
                                    Users.photo3.name -> user!!.photo3 = file.name
                                    Users.photo4.name -> user!!.photo4 = file.name
                                    Users.photo5.name -> user!!.photo5 = file.name
                                    Users.photo6.name -> user!!.photo6 = file.name
                                }
                            }
                        }
                }
                part.dispose()
            }
            call.respondRedirect(application.locations.href(UserUrl(username = photoUrl.username)))
        }
    }

}

fun Routing.reportRoute() {
    get<ReportUrl> { reportUrl ->
        val username = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {  UserRepository.report(reportUrl.username) }
            call.respondRedirect(application.locations.href(HomeUrl()))
        }
    }
}

fun Routing.bloqueRoute() {
    get<BloqueUrl> { bloqueUrl ->
        val username = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {  BloqueRepository.bloque(username, bloqueUrl.username) }
            call.respondRedirect(application.locations.href(HomeUrl()))
        }
    }
}

fun Routing.unbloqueRoute() {
    get<UnbloqueUrl> {unbloqueUrl ->
        val username = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction { BloqueRepository.unbloque(username, unbloqueUrl.username) }
            call.respondRedirect(application.locations.href(UserUrl(unbloqueUrl.username)))
        }
    }
}