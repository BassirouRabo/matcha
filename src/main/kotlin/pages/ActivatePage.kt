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

suspend fun ApplicationCall.activatePage(username: String) {
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
                                            h3 { + "Activate" }
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
                                                            + "activate"
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