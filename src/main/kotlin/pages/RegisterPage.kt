package pages

import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.request.uri
import io.ktor.response.respondText
import kotlinx.html.body
import kotlinx.html.*
import kotlinx.html.head
import kotlinx.html.title
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction
import repository.UserRepository
import RegisterUrl

suspend fun ApplicationCall.registerPage() {
    respondHtml {
        head {
            title {  + "Register pager" }
        }
        body {
            h1 { + "Register page" }
            form(locations.href(RegisterUrl()), encType = FormEncType.multipartFormData, method = FormMethod.post) {
                div{
                    p{
                        textInput(name = "username") {
                            placeholder = "Username"
                            required = true
                        }
                    }
                    p {
                        emailInput ( name = "email" ) {
                            placeholder = "email"
                            required = true
                        }
                    }
                    p {
                        textInput(name = "firstName") {
                            placeholder = "firstName"
                            required = true
                        }
                    }
                    p {
                        textInput(name = "lastName") {
                            placeholder = "lastName"
                            required = true
                        }
                    }
                    p {
                        numberInput(name = "age") {
                            placeholder = "age"
                            min = "0"
                            required = true
                        }
                    }
                    p {
                        passwordInput(name = "password") {
                            placeholder = "password"
                            required = true
                        }
                    }
                    p {
                        fileInput(name = "photo") {
                            placeholder = "photo"
                        }
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
}