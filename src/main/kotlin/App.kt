import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.content.default
import io.ktor.content.files
import io.ktor.content.static
import io.ktor.features.ConditionalHeaders
import io.ktor.features.DefaultHeaders
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie

@Location("/")
class HomeUrl()

@Location("/login")
class LoginUrl()

@Location("/logout")
class LogoutUrl()

@Location("/register")
class RegisterUrl()

@Location("/{username}/activate")
class ActivateUrl(val username: String)

@Location("/{username}/like")
class LikeUrl(val username: String)

@Location("/{username}/unlike")
class UnLikeUrl(val username: String)

@Location("/{username}")
data class UserUrl(val username: String)

@Location("/{username}/chats")
data class ChatUrl(val username: String)

data class Session(val username: String)

fun Application.main() {
    db.connect()
    db.init()

    install(DefaultHeaders)
    // install(CallLogging)
    install(Locations)
    install(ConditionalHeaders)
    install(Sessions) {
        cookie<Session>(SESSION)
    }
    install(Routing) {
        static("public") {
            files("src/main/resources")
            default("index.html")
        }
        homeRoute()
        loginRoute()
        logoutRoute()
        registerRoute()
        activateRoute()
        userRoute()
        chatRoute()
        likeRoute()
        unlikeRoute()
    }
}




