package pages

import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.request.uri
import io.ktor.response.respondText
import kotlinx.html.*
import ActivateUrl
import RegisterUrl
import data.Users

suspend fun ApplicationCall.activatePage() {
    respondHtml {
        head {
            title("Activate Page")
        }
        body {
            div {
                h1 { + "Activate page"}
                form(locations.href(ActivateUrl()), encType = FormEncType.multipartFormData, method = FormMethod.post) {

                    textInput(name = Users.username.name) {
                        placeholder = "username"
                    }
                    br
                    numberInput {
                        name = Users.code.name
                        placeholder = "code"
                        min = "1000"
                        max = "9999"
                        minLength = "4"
                        maxLength = "4"
                        required = true
                    }
                    br
                    submitInput { value = "Send" }
                }
            }
            p {
                strong {
                    a(href = application.locations.href(RegisterUrl())) {
                        + "REGISTER"
                    }
                }
            }
        }
        request.uri
    }
}