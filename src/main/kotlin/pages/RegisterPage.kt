package pages

import Campus
import Gender
import LoginUrl
import RegisterUrl
import data.Users
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import kotlinx.html.*
import template.headTemplate
import template.registerFootTemplate
import template.registerLeftTemplate
import template.scripTempate

suspend fun ApplicationCall.registerPage() {
    respondHtml {

        head { headTemplate("Register") }

        body(classes = "sign-in") {
            div(classes = "wrapper") {
                div(classes = "sign-in-page") {
                    div(classes = "signin-popup") {
                        div(classes = "signin-pop") {
                            div(classes = "row") {
                                registerLeftTemplate()
                                div(classes = "col-lg-6") {
                                    div(classes = "login-sec") {
                                        div(classes = "sign_in_sec current") {
                                            h3 { +"Register" }
                                            form(locations.href(RegisterUrl()), encType = FormEncType.multipartFormData, method = FormMethod.post) {
                                                div(classes = "row") {
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            textInput {
                                                                name = Users.firstName.name
                                                                placeholder = "First Name"
                                                                required = true
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            textInput {
                                                                name = Users.lastName.name
                                                                placeholder = "Last Name"
                                                                required = true
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            textInput {
                                                                name = Users.username.name
                                                                placeholder = "Username"
                                                                required = true
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            emailInput {
                                                                name = Users.email.name
                                                                placeholder = "Email"
                                                                required = true
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            numberInput {
                                                                name = Users.age.name
                                                                placeholder = "Age"
                                                                min = "18"
                                                                max = "100"
                                                                required = true
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            select {
                                                                name = Users.campus.name
                                                                required = true
                                                                option {
                                                                    value = Campus.PARIS.toString()
                                                                    +"Paris"
                                                                }
                                                                option {
                                                                    value = Campus.FREMONT.toString()
                                                                    +"Fremont"
                                                                }
                                                            }
                                                            i(classes = "la la-globe") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            select {
                                                                name = Users.gender.name
                                                                required = true
                                                                option {
                                                                    value = Gender.MALE.toString()
                                                                    +"Male"
                                                                }
                                                                option {
                                                                    value = Gender.FEMALE.toString()
                                                                    +"Female"
                                                                }
                                                            }
                                                            i(classes = "la la-globe") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            passwordInput(classes = "") {
                                                                name = Users.password.name
                                                                placeholder = "Password"
                                                            }
                                                            i(classes = "la la-lock") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        button() {
                                                            type = ButtonType.submit
                                                            value = "submit"
                                                            +"Register"
                                                        }
                                                    }
                                                    div(classes = "message-btn") {
                                                        a {
                                                            href = locations.href(LoginUrl())
                                                            title = "Login"
                                                            i(classes = "la la-sign-out") {}
                                                            +"Login"
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
                    registerFootTemplate()
                }
            }
            scripTempate()
        }

    }
}