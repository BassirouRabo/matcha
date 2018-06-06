package pages

import Session
import UserUrl
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion
import data.User
import getFriends
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.*
import template.*
import kotlinx.html.*
import kotlinx.html.dom.*
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

suspend fun ApplicationCall.chatPage(user1: User, user2: User, chats : List<data.Chat>) {
    val username = sessions.get<Session>()!!.username

    respondHtml {
        head { headTemplate("Chat") }

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
                                div(classes = "col-lg-3") {
                                    sideNotificationTemplate(user1, getFriends(user1.username))
                                }
                                div(classes = "col-lg-9 col-md-12 pd-right-none pd-left-none") {
                                    div(classes = "main-conversation-box") {
                                        div(classes = "message-bar-head") {
                                            div(classes = "usr-msg-details") {
                                                div(classes = "usr-ms-img") {
                                                    img {
                                                        src = if (user2.photo == "default") "/public/photos/50x50.png" else "/public/photos/${user2.photo}"
                                                        alt = user2.username
                                                    }
                                                }
                                                div(classes = "usr-mg-info") {
                                                    h3 { + user2.username }
                                                    p { + "Online" }
                                                }
                                            }
                                            a {
                                                href = "#"
                                                title = "options"
                                                i(classes = "fa fa-ellipsis-v")
                                            }
                                        }
                                        div(classes = "messages-line"){
                                            chats.forEach { if (it.username1 == username) chatOutTemplate(it) else if (it.username2 == username) chatInTemplate(it) }
                                            div{id =  "chats"}
                                        }
                                        div(classes = "message-send-area") {
                                            div(classes = "mf-field") {
                                                textInput {
                                                    id = "text"
                                                    name = "message"
                                                    placeholder = "Type a message here..."
                                                }
                                                button {
                                                    id = "btn"
                                                    +"Send"
                                                }
                                                hiddenInput {
                                                    id = "date"
                                                    value =  DateTime().toString(DateTimeFormat.shortDateTime())
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                hiddenInput {
                    id = "username1"
                    value = user1.username
                }

                hiddenInput {
                    id = "username2"
                    value = user2.username
                }

                footerTemplate()
            }

            scripTempate()
        }
    }
}