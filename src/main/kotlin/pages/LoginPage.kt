package pages

import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.request.uri
import io.ktor.response.respondText
import kotlinx.html.*
import LoginUrl
import RegisterUrl
import data.Users

suspend fun ApplicationCall.loginPage() {
    respondHtml {
        head {
            title {  + "Login pager" }
        }
        body {
            h1 { + "Login page" }
            form(locations.href(LoginUrl()), encType = FormEncType.multipartFormData, method = FormMethod.post) {
                div{
                    p{
                        textInput(name = Users.username.name) {
                            placeholder = "Username"
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
                        submitInput { value = "Login"}
                    }
                }
            }
            p{
                strong {
                    a(href = application.locations.href(RegisterUrl())) {
                        + "REGISTER"
                    }
                }
            }

        }
    }
}