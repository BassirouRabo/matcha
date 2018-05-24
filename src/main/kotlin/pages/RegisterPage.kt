package pages

import data.Users
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import kotlinx.html.body
import kotlinx.html.*
import kotlinx.html.head
import kotlinx.html.title
import RegisterUrl

suspend fun ApplicationCall.registerPage() {
    respondHtml {

        head {
            meta(charset = "UTF-8")
            title { +"42 Date | Sign in" }
            meta(name = "viewport") {
                content = "width=device-width, initial-scale=1.0"
            }
            meta(name = "description") {
                content = ""
            }
            meta(name = "keywords") {
                content = ""
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/animate.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/bootstrap.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/flatpickr.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/line-awesome.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/line-awesome-font-awesome.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/font-awesome.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/lib/slick/slick.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/lib/slick/slick-theme.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/style.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "/public/css/responsive.css"
            }
        }

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
                                            h3 { + "Register" }
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
                                                            +"Get Started"
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
            script(type = "text/javascript") { src = "/public/js/jquery.min.js" }
            script(type = "text/javascript") { src = "/public/js/popper.js" }
            script(type = "text/javascript") { src = "/public/js/bootstrap.min.js" }
            script(type = "text/javascript") { src = "/public/js/script.js" }
        }

    }
}