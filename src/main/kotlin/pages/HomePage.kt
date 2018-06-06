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
import HomeUrl

suspend fun ApplicationCall.homePage(currentUser: User, users: List<User>, friends: List<User>) {
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
                                    + "Search"
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
                                        form(locations.href(HomeUrl()), encType = FormEncType.multipartFormData, method = FormMethod.post){
                                            div(classes = "filter-secs full-width") {
                                                div(classes = "filter-heading") {
                                                    h3 { +"Filters" }
                                                }
                                                div(classes = "paddy") {
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { + "Campus" }
                                                        }
                                                        ul(classes = "avail-checks") {
                                                            li {
                                                                radioInput {
                                                                    name = "campus"
                                                                    id = "campusPa"
                                                                    checked = true
                                                                    value = Campus.PARIS.toString()
                                                                }
                                                                label {
                                                                    htmlFor = "campusPa"
                                                                    span { }
                                                                }
                                                                small { + "Paris" }
                                                            }
                                                            li {
                                                                radioInput {
                                                                    name = "campus"
                                                                    id = "campusFr"
                                                                    value = Campus.FREMONT.toString()
                                                                }
                                                                label {
                                                                    htmlFor = "campusFr"
                                                                    span { }
                                                                }
                                                                small { + "Fremont" }
                                                            }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { + "Orientation" }
                                                        }
                                                        ul(classes = "avail-checks") {
                                                            li {
                                                                radioInput {
                                                                    name = "orientation"
                                                                    id = "orientationBi"
                                                                    checked = true
                                                                    value = Orientation.BI.toString()
                                                                }
                                                                label {
                                                                    htmlFor = "orientationBi"
                                                                    span { }
                                                                }
                                                                small { + "BI" }
                                                            }
                                                            li {
                                                                radioInput {
                                                                    name = "orientation"
                                                                    id = "orientationHo"
                                                                    value = Orientation.HO.toString()
                                                                }
                                                                label {
                                                                    htmlFor = "orientationHo"
                                                                    span { }
                                                                }
                                                                small { + "HO" }
                                                            }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { + "Gender" }
                                                        }
                                                        ul(classes = "avail-checks") {
                                                            li {
                                                                radioInput {
                                                                    name = "gender"
                                                                    id = "genderMa"
                                                                    checked = true
                                                                    value = Gender.MALE.toString()
                                                                }
                                                                label {
                                                                    htmlFor = "genderMa"
                                                                    span { }
                                                                }
                                                                small { +"Male" }
                                                            }
                                                            li {
                                                                radioInput {
                                                                    name = "gender"
                                                                    id = "genderFe"
                                                                    value = Gender.FEMALE.toString()
                                                                }
                                                                label {
                                                                    htmlFor = "genderFe"
                                                                    span { }
                                                                }
                                                                small { +"Female" }
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
                                                                required = true
                                                                name = "range"
                                                            }
                                                        }
                                                        div(classes = "rg-limit") {
                                                            h4 { + "1" }
                                                            h4 { + "100" }
                                                        }
                                                    }
                                                    div(classes = "filter-dd") {
                                                        div(classes = "filter-ttl") {
                                                            h3 { + "Tags" }
                                                        }
                                                        ul(classes = "avail-checks") {
                                                            li {
                                                                checkBoxInput { name = "tagBio" }
                                                                small { +"Bio diet" }
                                                            }
                                                            li {
                                                                checkBoxInput { name = "tagGeek" }
                                                                small { + "Is geek" }
                                                            }
                                                            li {
                                                                checkBoxInput { name = "tagPiercing" }
                                                                small { + "has piercing" }
                                                            }
                                                            li {
                                                                checkBoxInput { name = "tagSmart" }
                                                                small { + "Is smart" }
                                                            }
                                                            li {
                                                                checkBoxInput { name = "tagShy" }
                                                                small { + "Is shy" }
                                                            }
                                                        }
                                                    }
                                                    div(classes = "message-btn") {
                                                        button {
                                                            + "Send"
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
                                                                        br {  }
                                                                        span {
                                                                            i(classes = "la la-star") {}
                                                                            + user.score.toString()
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
                                        sideNotificationTemplate(currentUser, friends)
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
