package pages


import io.ktor.application.ApplicationCall
import io.ktor.request.uri
import io.ktor.response.respondText
import Gender.*
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction
import repository.UserRepository
import data.User
import data.UserData
import io.ktor.html.respondHtml
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.*
import Session
import io.ktor.locations.locations
import LogoutUrl


suspend fun ApplicationCall.homePage() {
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
        }
    }
}
