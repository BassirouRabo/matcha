
import data.User
import data.UserData
import data.Users
import io.ktor.application.ApplicationCall
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.locations
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.request.receiveParameters
import io.ktor.request.uri
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import org.jetbrains.exposed.sql.transactions.transaction
import pages.*
import repository.UserRepository
import Gender.*
import Campus.*
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.and
import repository.LikeRepository
import repository.UserRepository.toUserdate
import repository.UserRepository.userToUserdate
import repository.VisitRepository
import java.util.logging.Logger

fun Routing.homeRoute() {
    location<HomeUrl> {
        get {
            if (call.sessions.get<Session>() == null)
                call.respondRedirect(application.locations.href(LoginUrl()))
            else
            {
                var users: List<User> = listOf()
                transaction {
                    users = UserRepository.getAll()
                }
                call.homePage(users, users, users)
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

            val username : String? = params[Users.username.name]
            val password : String? = params[Users.password.name]

            if (username == null || password == null) call.respondRedirect(application.locations.href(LoginUrl()))
            transaction {
                user = UserRepository.getByUsernameAndPassword(username!!, password!!)
            }
            if (user == null) call.respondRedirect(application.locations.href(LoginUrl()))
            else
            {
                if (user!!.isActivate)
                {
                    call.sessions.set(Session(username = user!!.username))
                    call.respondRedirect(application.locations.href(HomeUrl()))
                }
                else call.respondRedirect(application.locations.href(ActivateUrl(username!!)))
            }
        }
    }
}

fun Routing.logoutRoute() {
    location<LogoutUrl> {
        get{
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

            val username : String? = params[Users.username.name]
            val email : String? = params[Users.email.name]
            val firstName : String? = params[Users.firstName.name]
            val lastName : String? = params[Users.lastName.name]
            val age : Int? = params[Users.age.name]?.toInt()
            val password : String? = params[Users.password.name]
            val photo : String? = params[Users.photo.name]
            val gender : String? = params[Users.gender.name]
            val campus : String? = params[Users.campus.name]

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
                    if (user == null) UserRepository.add(UserData(username = username, email = email!!, firstName = firstName!!, lastName = lastName!!, age = age!!, password = password!!, biography = "My biography", photo = photo ?: "photo.jpg", isActivate = false, code = 1234, gender = if(gender.equals("Male")) MALE else FEMALE, campus = if(campus.equals("Paris")) PARIS else FREMONT))
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
        if (username == null)  call.respondRedirect(application.locations.href(LoginUrl()))
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
            else if (user!!.username.equals(session.username))
            {
                val likes: MutableList<User> = mutableListOf<User>()
                val likeds  : MutableList<User> = mutableListOf<User>()
                val visits : MutableList<User> = mutableListOf<User>()
                val visiteds : MutableList<User> = mutableListOf<User>()
                transaction {
                    VisitRepository.getVisits(session.username).forEach { visit ->
                        UserRepository.getByUsername(visit.username2)?.let { visits.add(UserRepository.getByUsername(visit.username2)!!) }
                    }
                    VisitRepository.getVisiteds(session.username).forEach { visited ->
                        UserRepository.getByUsername(visited.username1)?.let { visiteds.add(UserRepository.getByUsername(visited.username1)!!) }
                    }
                    LikeRepository.getLikes(session.username).forEach { like ->
                        UserRepository.getByUsername(like.username2)?.let { likes.add(UserRepository.getByUsername(like.username2)!!) }
                    }
                    LikeRepository.getLikeds(session.username).forEach { liked ->
                        UserRepository.getByUsername(liked.username1)?.let { likes.add(UserRepository.getByUsername(liked.username1)!!) }
                    }
                }
                call.profilPage(user!!, likes, likeds, visits, visiteds)
            }
            else
            {
                transaction {
                    VisitRepository.add(session.username, user!!.username)
                }
                call.userPage(user!!)
            }
        }
    }

    post<UserUrl> { userUrl ->
        userUrl.username
    }
}

fun Routing.chatRoute() {
    get<ChatUrl> {chatUrl ->
        call.chatPage()
    }
}