
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
        get{
            call.loginPage()
        }
        post {
            var user: User? = null
            val params = call.receiveParameters()

            val username : String? = params[Users.username.name]
            val password : String? = params[Users.password.name]

            if (username == null || password == null)
                call.respondRedirect(application.locations.href(LoginUrl()))
            transaction {
                user = UserRepository.getByUsernameAndPassword(username!!, password!!)
            }
            if (user == null)
                call.respondRedirect(application.locations.href(LoginUrl()))
            else
            {
                call.sessions.set(Session(username = user!!.username))
                call.respondRedirect(application.locations.href(HomeUrl()))
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
            val biographie : String? = params[Users.biography.name]
        }
    }
}

fun Routing.activateRoute() {
    location<ActivateUrl> {
        get {
            call.activatePage()
        }

        post {

        }
    }
}

fun Routing.userRoute() {
    get<UserUrl> { userUrl ->
        if (call.sessions.get<Session>() == null)
            call.respondRedirect(application.locations.href(LoginUrl()))
        else
            call.userPage(userUrl)
    }

    post<UserUrl> { userUrl ->
        userUrl.username
    }
}

