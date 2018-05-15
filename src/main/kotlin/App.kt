
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.receiveOrNull
import io.ktor.request.uri
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import org.jetbrains.exposed.sql.Database

@Location("/") class homeUrl()
@Location("/login") class LoginUrl()
@Location("/logout") class LogoutUrl()
@Location("/register") class RegisterUrl()
@Location("/activate") class ActivateUrl()
@Location("/{username}") data class UserUrl(val username : String)

data class Session(val user: String)

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
        homeRoute()
        loginRoute()
        logoutRoute()
        registerRoute()
        activateRoute()
        userRoute()
    }
}




