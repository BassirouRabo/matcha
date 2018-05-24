package pages

import ActivateUrl
import LoginUrl
import RegisterUrl
import data.Users
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import kotlinx.html.*
import template.headTemplate
import template.scripTempate

suspend fun ApplicationCall.activatePage(username: String) {
    respondHtml {

        head { headTemplate("Activate account") }

        body(classes = "sign-in") {
            div(classes = "wrapper") {
                div(classes = "sign-in-page") {
                    div(classes = "signin-popup") {
                        div(classes = "signin-pop") {
                            div(classes = "row") {
                                div(classes = "col-lg-6") {
                                    div(classes = "cmp-info") {
                                        div(classes = "cm-logo") { }
                                        img(src = "/public/images/logo_42_dating.png") {
                                            alt = ""
                                        }
                                    }
                                }
                                div(classes = "col-lg-6") {
                                    div(classes = "login-sec") {
                                        div(classes = "sign_in_sec current") {
                                            h3 { +"Activate" }
                                            form(locations.href(ActivateUrl(username)), encType = FormEncType.multipartFormData, method = FormMethod.post) {
                                                div(classes = "row") {
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            numberInput() {
                                                                name = Users.code.name
                                                                placeholder = "code"
                                                                min = "1000"
                                                                max = "9999"
                                                                minLength = "4"
                                                                maxLength = "4"
                                                                required = true
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        button() {
                                                            type = ButtonType.submit
                                                            value = "submit"
                                                            +"activate"
                                                        }
                                                    }
                                                    div(classes = "message-btn") {
                                                        a {
                                                            href = locations.href(LoginUrl())
                                                            title = "Login"
                                                            i(classes = "la la-sign-out") {}
                                                            +"Login"
                                                        }
                                                        a {
                                                            href = locations.href(RegisterUrl())
                                                            title = "Register"
                                                            i(classes = "la la-sign-out") {}
                                                            +"Register"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    div(classes = "footy-sec") {
                        div(classes = "container") {
                            ul {
                                li {
                                    a(href = "#") {
                                        title = ""
                                        +"School"
                                    }
                                }
                                li {
                                    a(href = "#") {
                                        title = ""
                                        +"Privacy Policy"
                                    }
                                }
                                li {
                                    a(href = "#") {
                                        title = ""
                                        +"Campus"
                                    }
                                }
                                li {
                                    a(href = "#") {
                                        title = ""
                                        +"Cookies Policy"
                                    }
                                }
                                li {
                                    a(href = "#") {
                                        title = ""
                                        +"Shop"
                                    }
                                }
                            }
                            p {
                                img(src = "/public/images/copy-icon.png") {
                                    alt = ""
                                    +"Copyright @brabo-hi 2018"
                                }
                            }
                        }
                    }
                }
            }
            scripTempate()
        }
    }
}