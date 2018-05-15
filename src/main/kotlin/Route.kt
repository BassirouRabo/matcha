
import io.ktor.application.ApplicationCall
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.locations.get
import io.ktor.locations.locations
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.uri
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import pages.*

fun Routing.homeRoute() {
    location<homeUrl> {
        get {
            /*if (call.sessions.get<Session>() == null)
                call.respondRedirect(application.locations.href(LoginUrl()))
            else
                call.homePage()*/

        }
    }
}

fun Routing.loginRoute() {
    location<LoginUrl> {
        get{
            call.loginPage()
        }
        post {

        }
    }
}

fun Routing.logoutRoute() {
    location<LogoutUrl> {
        get{
            call.sessions.clear<Session>()
            call.respondRedirect(application.locations.href(LoginUrl()))
        }
    }
}

fun Routing.registerRoute() {
    location<RegisterUrl> {
        get{
            call.registerPage()
        }

        post {

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

