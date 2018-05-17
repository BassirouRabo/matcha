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
import data.Users

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
                        textInput(name = Users.username.name) {
                            placeholder = "Username"
                            required = true
                        }
                    }
                    p {
                        emailInput ( name = Users.email.name ) {
                            placeholder = "email"
                            required = true
                        }
                    }
                    p {
                        textInput(name = Users.firstName.name) {
                            placeholder = "firstName"
                            required = true
                        }
                    }
                    p {
                        textInput(name = Users.lastName.name) {
                            placeholder = "lastName"
                            required = true
                        }
                    }
                    p {
                        + "Gender"
                        br
                        radioInput(name = Users.gender.name) {
                            value = "M"
                            checked = true
                            + "M"
                        }
                        br
                        radioInput(name = Users.gender.name) {
                            value = "F"
                            + "F"
                        }
                    }
                    p {
                        numberInput(name = Users.age.name) {
                            placeholder = "age"
                            min = "0"
                            max = "100"
                            required = true
                        }
                    }
                    p {
                        passwordInput(name = Users.password.name) {
                            placeholder = "password"
                            required = true
                        }
                    }
                    p {
                        fileInput(name = Users.photo.name) {
                            placeholder = "photo"
                        }
                    }
                    p {
                        textInput(name = Users.biography.name) {
                            placeholder = "Biography"
                        }
                    }
                    p {
                        submitInput { value = "Register"}
                    }
                }
            }

        }
    }
}