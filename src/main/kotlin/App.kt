
import io.ktor.application.*
import io.ktor.content.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.routing.*
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import java.io.File

@Location("/") class HomeUrl()
@Location("/login") class LoginUrl()
@Location("/logout") class LogoutUrl()
@Location("/register") class RegisterUrl()
@Location("/activate") class ActivateUrl()
@Location("/{username}") data class UserUrl(val username : String)

data class Session(val username: String)

fun Application.main() {
    db.connect()
    db.init()

    install(DefaultHeaders)
    install(CallLogging)
    install(Locations)
    install(ConditionalHeaders)
    install(Sessions) {
        cookie<Session>(SESSION)
    }
    install(Routing) {
        static("public") {
            files("src/main/resources")
        }
        homeRoute()
        loginRoute()
        logoutRoute()
        registerRoute()
        activateRoute()
        userRoute()
    }
}




