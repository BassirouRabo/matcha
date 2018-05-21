
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
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.and
import repository.UserRepository.toUserdate
import repository.UserRepository.userToUserdate
import java.util.logging.Logger

fun Routing.homeRoute() {
    location<HomeUrl> {
        get {
            if (call.sessions.get<Session>() == null)
                call.respondRedirect(application.locations.href(LoginUrl()))
            else
                call.homePage()
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
                else call.respondRedirect(application.locations.href(ActivateUrl()))
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
   location<RegisterUrl> {
        get{
            call.registerPage()
        }

        post {
            val params = call.receiveParameters()

            val username : String? = params[Users.username.name]
            val email : String? = params[Users.email.name]
            val firstName : String? = params[Users.firstName.name]
            val lastName : String? = params[Users.lastName.name]
            val age : Int? = params[Users.age.name]?.toInt()
            val password : String? = params[Users.password.name]
            val photo : String? = params[Users.photo.name]
            val gender : String? = params[Users.gender.name]
            val biographie : String? = params[Users.biography.name]

            if (username.equals(null)
                    || email.equals(null)
                    || firstName.equals(null)
                    || lastName.equals(null)
                    || age == 0
                    || password.equals(null)
                    || gender.equals(null)
                    || biographie.equals(null)) {
                call.respondRedirect(application.locations.href(RegisterUrl()))
            } else {
                transaction {
                    logger.addLogger(StdOutSqlLogger)
                    val user: User? = UserRepository.getByUsername(username!!)
                    if (user == null) UserRepository.add(UserData(username = username, email = email!!, firstName = firstName!!, lastName = lastName!!, age = age!!, password = password!!, biography = biographie!!, photo = photo ?: "photo.jpg", isActivate = false, code = 1234, gender = if(gender.equals("M")) M else F))
                }
                call.respondRedirect(application.locations.href(LoginUrl()))
            }
        }
    }
}

fun Routing.activateRoute() {
    location<ActivateUrl> {
        get {
            call.activatePage()
        }

        post {
            var user: User? = null
            val params = call.receiveParameters()
            val username = params[Users.username.name]
            val code = params[Users.code.name]
            if (username == null || code == null) call.respondRedirect(application.locations.href(ActivateUrl()))
            else {
                transaction {
                    user = UserRepository.get(Users.username.eq(username) and Users.code.eq(code.toInt()))
                }
                if (user == null) {
                    call.respondRedirect(application.locations.href(ActivateUrl()))
                } else {
                    transaction {
                        user!!.isActivate = true
                        user = UserRepository.update(username, toUserdate(user!!))
                    }
                    if (user == null || !user!!.isActivate) {
                        call.respondRedirect(application.locations.href(ActivateUrl()))
                    } else call.respondRedirect(application.locations.href(HomeUrl()))
                }
            }
        }
    }
}

fun Routing.userRoute() {
    get<UserUrl> { userUrl ->
        var user: User? = null
        if (call.sessions.get<Session>() == null) call.respondRedirect(application.locations.href(LoginUrl()))
        else {
            transaction {
                user = UserRepository.getByUsername(userUrl.username)
            }
            if (user == null)
            {
                println("$$$$$$")
                call.respondRedirect(application.locations.href(HomeUrl()))
            }
            else
            {
                println("###")
                call.userPage(user!!)
            }
        }
    }

    post<UserUrl> { userUrl ->
        userUrl.username
    }
}

