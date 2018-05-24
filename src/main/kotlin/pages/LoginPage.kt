package pages

import LoginUrl
import RegisterUrl
import data.Users
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import kotlinx.html.*
import template.headTemplate
import template.scripTempate

suspend fun ApplicationCall.loginPage() {
    respondHtml {

        head { headTemplate("Login") }

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
                                            h3 { +"Sign in" }
                                            form(locations.href(LoginUrl()), encType = FormEncType.multipartFormData, method = FormMethod.post) {
                                                div(classes = "row") {
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            textInput() {
                                                                name = Users.username.name
                                                                placeholder = "Username"
                                                                required = true
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            passwordInput {
                                                                name = Users.password.name
                                                                placeholder = "Password"
                                                                required = true
                                                            }
                                                            i(classes = "la la-lock") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        button() {
                                                            type = ButtonType.submit
                                                            value = "submit"
                                                            +"Sign in"
                                                        }
                                                    }
                                                    div(classes = "message-btn") {
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