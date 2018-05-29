import Campus.FREMONT
import Campus.PARIS
import Gender.FEMALE
import Gender.MALE
import data.User
import data.UserData
import data.Users
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.content.MultiPartData
import io.ktor.content.PartData
import io.ktor.content.forEachPart
import io.ktor.locations.get
import io.ktor.locations.location
import io.ktor.locations.locations
import io.ktor.locations.post
import io.ktor.network.util.ioCoroutineDispatcher
import io.ktor.request.isMultipart
import io.ktor.request.receiveMultipart
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.response.respondWrite
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.withContext
import kotlinx.coroutines.experimental.yield
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import pages.*
import repository.BloqueRepository
import repository.LikeRepository
import repository.UserRepository
import repository.UserRepository.toUserdate
import repository.VisitRepository
import java.io.File
import java.io.InputStream
import java.io.OutputStream

fun Routing.homeRoute() {
    location<HomeUrl> {
        get {
            var user : User? = null
            val username : String? = call.sessions.get<Session>()?.username
            if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
            else {
                var users: List<User> = listOf()
                transaction {
                    users = UserRepository.getAll()
                    user = UserRepository.getByUsername(username)
                }
                if (user!!.score == 0){
                    call.respondRedirect(application.locations.href(InfoUrl(username)))
                } else {
                    call.homePage(user!!, users, users)
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
                logger.addLogger(StdOutSqlLogger)
                val user: User? = UserRepository.getByUsername(username!!)
                if (user == null) UserRepository.add(UserData(username = username, email = email!!, firstName = firstName!!, lastName = lastName!!, age = age!!, password = password!!, biography = "My biography", photo = photo
                        ?: "default", isActivate = false, code = 1234, gender = if (gender.equals("Male")) MALE else FEMALE, campus = if (campus.equals("Paris")) PARIS else FREMONT))
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
        val session = call.sessions.get<Session>()
        if (session == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {
                user = UserRepository.getByUsername(userUrl.username)
            }
            if (user == null) call.respondRedirect(application.locations.href(HomeUrl()))
            else {
                if (user!!.username.equals(session.username)) {
                    val likes: MutableList<User> = mutableListOf<User>()
                    val likeds: MutableList<User> = mutableListOf<User>()
                    val visits: MutableList<User> = mutableListOf<User>()
                    val visiteds: MutableList<User> = mutableListOf<User>()
                    val bloques: MutableList<User> = mutableListOf<User>()
                    transaction {
                        VisitRepository.getVisits(session.username).forEach { UserRepository.getByUsername(it.username2)?.let { visits.add(it) } }
                        VisitRepository.getVisiteds(session.username).forEach { UserRepository.getByUsername(it.username1)?.let { visiteds.add(it) } }
                        LikeRepository.getLikes(session.username).forEach { UserRepository.getByUsername(it.username2)?.let { likes.add(it) } }
                        LikeRepository.getLikeds(session.username).forEach { UserRepository.getByUsername(it.username1)?.let { likes.add(it) } }
                        BloqueRepository.getBloques(session.username).forEach { UserRepository.getByUsername(it.username2)?.let { bloques.add(it) } }
                    }
                    call.profilPage(user!!, likes, likeds, visits, visiteds, bloques)
                } else {
                    transaction {
                        VisitRepository.add(session.username, user!!.username)
                    }
                    call.userPage(user!!)
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
        call.chatPage()
    }
}

fun Routing.likeRoute() {
    get<LikeUrl> { likeUrl ->
        val username = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {
                LikeRepository.like(username, likeUrl.username)
            }
            call.respondRedirect(application.locations.href(UserUrl(likeUrl.username)))
        }
    }
}

fun Routing.unlikeRoute() {
    get<UnLikeUrl> { unlikeUrl ->
        val username = call.sessions.get<Session>()?.username
        if (username == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {
                LikeRepository.unlike(username, unlikeUrl.username)
            }
            call.respondRedirect(application.locations.href(UserUrl(unlikeUrl.username)))
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
                user!!.tagBio = if (params[Users.tagBio.name] == null) false else true
                user!!.tagGeek = if (params[Users.tagGeek.name] == null) false else true
                user!!.tagPiercing = if (params[Users.tagPiercing.name] == null) false else true
                user!!.tagSmart = if (params[Users.tagSmart.name] == null) false else true
                user!!.tagShy = if (params[Users.tagShy.name] == null) false else true
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
        val uploadDir = File("src/main/resources/photos")
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
                                Users.email.name -> user!!.email = part.value
                                Users.age.name -> user!!.age = part.value.toInt()
                                Users.biography.name -> user!!.biography = part.value
                                Users.password.name -> user!!.password = part.value
                            }
                        }
                    }
                    is PartData.FileItem ->
                        if (!part.originalFileName.isNullOrEmpty()) {
                            val ext = File(part.originalFileName).extension
                            val file = File(uploadDir, "${user!!.username}-${System.currentTimeMillis()}.$ext")
                           // println("file.absolutePath [${file.absolutePath}]\nName [${file.name}]\nPath [${file.path}]")
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
