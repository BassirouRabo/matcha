package pages

import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.html.respondHtmlTemplate
import io.ktor.request.uri
import io.ktor.sessions.sessions
import kotlinx.html.*
import template.HomeTemplate
import Session
import io.ktor.locations.locations
import LogoutUrl
import data.User
import io.ktor.response.respondRedirect
import io.ktor.sessions.get
import org.jetbrains.exposed.sql.transactions.transaction
import repository.UserRepository
import UserUrl

suspend fun ApplicationCall.homePage() {
    var users: List<User> = listOf()
    transaction {
       users = UserRepository.getAll()
    }
    respondHtml {
        head {
            title("Home Page")
        }
        body {
            h1{
                + " HOME PAGE  ${request.uri}  "
            }

            p{
                + "Welcome "
                b { + "${sessions.get<Session>()?.username}"}
            }
            p{
                a(href = application.locations.href(LogoutUrl())) {
                    + "Logout"
                }
            }
            users.forEach {user ->
                p{
                    a(href = application.locations.href(UserUrl(user.username))) {
                        + user.username
                    }
                }
            }
        }

    }
}
