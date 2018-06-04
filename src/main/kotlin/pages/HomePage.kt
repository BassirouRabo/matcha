package pages

import Session
import UserUrl
import data.User
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openReadChannel
import io.ktor.network.sockets.openWriteChannel
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.coroutines.experimental.io.readASCIILine
import kotlinx.coroutines.experimental.io.writeBytes
import kotlinx.coroutines.experimental.launch
import kotlinx.html.*
import org.joda.time.format.DateTimeFormat
import template.*
import java.net.InetSocketAddress
import java.net.SocketAddress

suspend fun ApplicationCall.homePage(currentUser: User, users: List<User>, onlines: List<User>) {
    val username = sessions.get<Session>()!!.username

    respondHtml {

        head { headTemplate("Home") }

        body {

            div(classes = "wrapper") {

                headerTemplate(username)

                div(classes = "search-sec") {
                    div(classes = "container") {
                        div(classes = "search-box") {
                            form() {
                                textInput {
                                    name = "search"
                                    placeholder = "Search keywords"
                                    required = true
                                }
                                button {
                                    type = ButtonType.submit
                                    +"Search"
                                }
                            }
                        }
                    }
                }

                section {
                    div(classes = "main-section") {
                        div(classes = "container") {
                            div(classes = "main-section-data") {
                                div(classes = "row") {
                                    div(classes = "col-lg-3") {
                                        div(classes = "") {
                                            div(classes = "filter-secs full-width") {
                                                div(classes = "filter-heading") {
                                                    h3 { +"Filters" }
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        +"Clear all filters"
                                                    }
                                                }
                                                div(classes = "paddy") {
                                                    div(classes = "filter-dd") {
                                                        form {
                                                            textInput() {
                                                                name = "search-skills"
                                                                placeholder = "Search skills"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { +"Campus" }
                                                        }
                                                        ul(classes = "avail-checks") {
                                                            li {
                                                                radioInput() {
                                                                    name = "cc"
                                                                    id = "c1"
                                                                }
                                                                label {
                                                                    htmlFor = "c1"
                                                                    span { }
                                                                }
                                                                small { +"Paris" }
                                                            }
                                                            li {
                                                                radioInput {
                                                                    name = "cc"
                                                                    id = "c2"
                                                                }
                                                                label {
                                                                    htmlFor = "c2"
                                                                    span { }
                                                                }
                                                                small { +"USA" }
                                                            }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { +"Gender" }
                                                        }
                                                        ul(classes = "avail-checks") {
                                                            li {
                                                                radioInput {
                                                                    name = "cc"
                                                                    id = "c3"
                                                                }
                                                                label {
                                                                    htmlFor = "c3"
                                                                    span { }
                                                                }
                                                                small { +"Male" }
                                                            }
                                                            li {
                                                                radioInput {
                                                                    name = "cc"
                                                                    id = "c4"
                                                                }
                                                                label {
                                                                    htmlFor = "c4"
                                                                    span { }
                                                                }
                                                                small { +"Female" }
                                                            }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { +"Filter" }
                                                        }
                                                        form(classes = "job-tp") {
                                                            select {
                                                                option() { +"Filer 1" }
                                                                option() { +"Filer 2" }
                                                            }
                                                            i(classes = "fa fa-ellipsis-v") {
                                                                attributes["aria-hidden"] = "true"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { +"Age" }
                                                        }
                                                        div(classes = "rg-slider") {
                                                            hiddenInput(classes = "rn-slider slider-input") {
                                                                value = "5,50"
                                                            }
                                                        }
                                                        div(classes = "rg-limit") {
                                                            h4 { +"1" }
                                                            h4 { +"100" }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { +"Experience Level" }
                                                        }
                                                        form(classes = "job-tp") {
                                                            select {
                                                                option() { +"Select a experience level" }
                                                                option() { +"3 years" }
                                                            }
                                                            i(classes = "fa fa-ellipsis-v") {
                                                                attributes["aria-hidden"] = "true"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { +"Countries" }
                                                        }
                                                        form(classes = "job-tp") {
                                                            select {
                                                                option { +"Select a country" }
                                                                option { +"Paris" }
                                                                option { +"Fremont" }
                                                            }
                                                            i(classes = "fa fa-ellipsis-v") {
                                                                attributes["aria-hidden"] = "true"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "tags-sec full-width") {
                                                ul {
                                                    li {
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            +"Intra"
                                                        }
                                                    }
                                                    li {
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            +"School"
                                                        }
                                                    }
                                                    li {
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            +"Privacy"
                                                        }
                                                    }
                                                    li {
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            +"Campus"
                                                        }
                                                    }
                                                    li {
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            +"Cookies"
                                                        }
                                                    }
                                                    li {
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            +"Shop"
                                                        }
                                                    }
                                                    li {
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            +"Language"
                                                        }
                                                    }
                                                    li {
                                                        a(classes = "Language") {
                                                            href = "#"
                                                            title = ""
                                                            +"Developed by @brabo-hi"
                                                        }
                                                    }
                                                }
                                                div(classes = "cp-sec") {
                                                    p {
                                                        img(classes = "") {
                                                            alt = ""
                                                            src = "public/images/cp.png"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-6") {
                                        div(classes = "main-ws-sec") {
                                            div(classes = "posts-section") {
                                                users.forEach { user ->
                                                    div(classes = "post-bar") {
                                                        div(classes = "post_topbar") {
                                                            div(classes = "usy-dt") {
                                                                a {
                                                                    href = locations.href(UserUrl(user.username))
                                                                    img {
                                                                        src = if (user.photo == "default") "/public/images/profile_default.jpg" else "/public/photos/${user.photo}"
                                                                        alt = user.username
                                                                        width = "50"
                                                                        height = "50"
                                                                    }
                                                                    div(classes = "usy-name") {
                                                                        h3 { + user.username }
                                                                        span {
                                                                            i(classes = "fa fa-clock-o") {}
                                                                            if (user.isOnline)  + " online" else  + " ${user.date.toString(DateTimeFormat.shortDateTime())}"
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        div(classes = "job_descp") {
                                                            p { +user.biography }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-3") {
                                        sideNotificationTemplate(currentUser, onlines)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            scripTempate()

            hiddenTemplate(username, username)
        }

    }

}
