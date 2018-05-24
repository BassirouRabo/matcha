package pages

import Session
import UserUrl
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.*

suspend fun ApplicationCall.chatPage() {
    val username = sessions.get<Session>()!!.username

    respondHtml {
        head {
            meta(charset = "UTF-8")
            title { +"42 Date | Home" }
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
                href = "/public/css/jquery.range.css"
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
                href = "/public/css/jquery.mCustomScrollbar.min.css"
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

        body {
            div(classes = "wrapper") {

                header {
                    div(classes = "container") {
                        div(classes = "header-data") {
                            div(classes = "logo") {
                                a {
                                    href = "/"
                                    title = ""
                                    img(src = "/public/images/logo.png") {
                                        alt = ""
                                    }
                                }
                            }
                            div(classes = "user-account") {
                                div(classes = "user-info") {
                                    a {
                                        href = locations.href(UserUrl(username))
                                        title = username
                                        i(classes = "la la-tasks") {}
                                        +username.toUpperCase()
                                    }
                                }
                            }
                        }
                    }
                }

                section(classes = "messages-page") {
                    div(classes = "container") {
                        div(classes = "messages-sec") {
                            div(classes = "row") {
                                div(classes = "col-lg-4 col-md-12 no-pdd") {
                                    div(classes = "msgs-list") {
                                        div(classes = "msg-title") {
                                            h3 { +"Messages" }
                                            ul {
                                                li {
                                                    a {
                                                        href = "#"
                                                        title = ""
                                                        i(classes = "fa fa-cog")
                                                    }
                                                }
                                                li {
                                                    a {
                                                        href = "#"
                                                        title = ""
                                                        i(classes = "fa fa-ellipsis-v")
                                                    }
                                                }
                                            }
                                        }
                                        div(classes = "messages-list") {
                                            ul {
                                                li(classes = "active") {
                                                    div(classes = "usr-msg-details") {
                                                        div(classes = "usr-ms-img") {
                                                            img {
                                                                src = "http://via.placeholder.com/50x50"
                                                                alt = ""
                                                            }
                                                            span(classes = "msg-status")
                                                        }
                                                        div(classes = "usr-mg-info") {
                                                            h3 { +"Brabo-hi" }
                                                            p { +"Un long message ici..." }
                                                        }
                                                        span(classes = "posted_time") { +"1:55 PM" }
                                                        span(classes = "msg-notifc") { +"1" }
                                                    }
                                                }
                                                li(classes = "") {
                                                    div(classes = "usr-msg-details") {
                                                        div(classes = "usr-ms-img") {
                                                            img {
                                                                src = "http://via.placeholder.com/50x50"
                                                                alt = ""
                                                            }
                                                            span(classes = "msg-status")
                                                        }
                                                        div(classes = "usr-mg-info") {
                                                            h3 { +"Brabo-hi" }
                                                            p { +"Un long message ici..." }
                                                        }
                                                        span(classes = "posted_time") { +"1:55 PM" }
                                                        span(classes = "msg-notifc") { +"1" }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                div(classes = "col-lg-8 col-md-12 pd-right-none pd-left-none") {
                                    div(classes = "main-conversation-box") {
                                        div(classes = "message-bar-head") {
                                            div(classes = "usr-msg-details") {
                                                div(classes = "usr-ms-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/50x50"
                                                        alt = ""
                                                    }
                                                }
                                                div(classes = "usr-mg-info") {
                                                    h3 { +"Brabo Hi" }
                                                    p { +"Online" }
                                                }
                                            }
                                            a {
                                                href = "#"
                                                title = ""
                                                i(classes = "fa fa-ellipsis-v")
                                            }
                                        }
                                        div(classes = "messages-line") {
                                            div(classes = "main-message-box ta-right") {
                                                div(classes = "message-dt") {
                                                    div(classes = "message-inner-dt") {
                                                        p { +"Zappy is an entirely automatic game where some computer programs play amongst them-  selves. The game speed is defined by a time unit." }
                                                    }
                                                    span { +"Sat, Aug 23, 1:08 PM" }
                                                }
                                                div(classes = "messg-usr-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/50x50"
                                                    }
                                                }
                                            }
                                            div(classes = "main-message-box st3") {
                                                div(classes = "message-dt st3") {
                                                    div(classes = "message-inner-dt") {
                                                        p { +"Un long messge ici" }
                                                    }
                                                    span { +"2 minutes ago" }
                                                }
                                                div(classes = "messg-usr-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/50x50"
                                                        alt = ""
                                                    }
                                                }
                                            }
                                            div(classes = "main-message-box ta-right") {
                                                div(classes = "message-dt") {
                                                    div(classes = "message-inner-dt") {
                                                        p { +"Zappy is an entirely automatic game where some computer programs play amongst them-  selves. The game speed is defined by a time unit." }
                                                    }
                                                    span { +"Sat, Aug 23, 1:08 PM" }
                                                }
                                                div(classes = "messg-usr-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/50x50"
                                                    }
                                                }
                                            }
                                            div(classes = "main-message-box st3") {
                                                div(classes = "message-dt st3") {
                                                    div(classes = "message-inner-dt") {
                                                        p { +"..." }
                                                    }
                                                    span { +"Typing..." }
                                                }
                                                div(classes = "messg-usr-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/50x50"
                                                        alt = ""
                                                    }
                                                }
                                            }
                                            div(classes = "main-message-box ta-right") {
                                                div(classes = "message-dt") {
                                                    div(classes = "message-inner-dt") {
                                                        p { +"Zappy is an entirely automatic game where some computer programs play amongst them-  selves. The game speed is defined by a time unit." }
                                                    }
                                                    span { +"Sat, Aug 23, 1:08 PM" }
                                                }
                                                div(classes = "messg-usr-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/50x50"
                                                    }
                                                }
                                            }
                                            div(classes = "main-message-box st3") {
                                                div(classes = "message-dt st3") {
                                                    div(classes = "message-inner-dt") {
                                                        p { +"..." }
                                                    }
                                                    span { +"Typing..." }
                                                }
                                                div(classes = "messg-usr-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/50x50"
                                                        alt = ""
                                                    }
                                                }
                                            }
                                        }
                                        div(classes = "message-send-area") {
                                            form {
                                                div(classes = "mf-field") {
                                                    textInput {
                                                        name = "message"
                                                        placeholder = "Type a message here"
                                                    }
                                                    button {
                                                        type = ButtonType.submit
                                                        +"Send"
                                                    }
                                                }
                                                ul {
                                                    li {
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-smile-o")
                                                        }
                                                    }
                                                    li {
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-camera")
                                                        }
                                                    }
                                                    li {
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-paperclip")
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
                }

                footer {
                    div(classes = "footy-sec mn no-margin") {
                        div(classes = "container") {
                            ul {
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        +"Intra"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        +"School"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        +"Privacy Policy"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        +"Cookies Policy"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        +"Shop"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        +"Developed by @brabo-hi"
                                    }
                                }
                            }
                            p {
                                +"Copyright 2018"
                                img {
                                    src = "/public/images/copy-icon2.png"
                                    alt = ""
                                }
                            }
                        }
                    }
                }

                script(type = "text/javascript") { src = "/public/js/jquery.min.js" }
                script(type = "text/javascript") { src = "/public/js/popper.js" }
                script(type = "text/javascript") { src = "/public/js/bootstrap.min.js" }
                script(type = "text/javascript") { src = "/public/js/jquery.mCustomScrollbar.js" }
                script(type = "text/javascript") { src = "/public/js/jquery.range-min.js" }
                script(type = "text/javascript") { src = "/public/js/flatpickr.min.js" }
                script(type = "text/javascript") { src = "/public/lib/slick/slick.min.js" }
                script(type = "text/javascript") { src = "/public/js/scrollbar.js" }
                script(type = "text/javascript") { src = "/public/js/script.js" }
            }
        }
    }
}