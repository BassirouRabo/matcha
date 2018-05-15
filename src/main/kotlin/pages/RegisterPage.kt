package pages

import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.request.uri
import io.ktor.response.respondText
import kotlinx.html.body
import kotlinx.html.*
import kotlinx.html.head
import kotlinx.html.title
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction
import repository.UserRepository

suspend fun ApplicationCall.registerPage() {

    transaction {
        logger.addLogger(StdOutSqlLogger)
        val user = UserRepository.get(1)
        println("  USER " + user?.username)
    }

    respondHtml {
        head {
            title {  + "Register pager" }
        }
        body {
            h1 { + "Register page" }
            div{
                p{
                    textInput(name = "username") { placeholder = "Username" }
                }
                p {
                    emailInput ( name = "email" ) { placeholder = "email" }
                }
                p {
                    textInput(name = "firstName") { placeholder = "firstName" }
                }
                p {
                    textInput(name = "lastName") { placeholder = "lastName" }
                }
                p {
                    numberInput(name = "age") {
                        placeholder = "age"
                        min = "0"
                    }
                }
                p {
                    passwordInput(name = "password") {
                        placeholder = "password" }
                }
                p {
                    fileInput(name = "photo") { placeholder = "photo" }
                }
                p {
                    textInput(name = "biographie")
                }
                p {
                    submitInput { value = "Register"}
                }
            }
        }
    }
}