package template

import data.User
import io.ktor.locations.locations
import kotlinx.html.*
import org.joda.time.format.DateTimeFormat

fun DIV.sideNotificationTemplate(currentUser: User, onlines: List<User>) {
    div(classes = "suggestionsth full-width") {
        div(classes = "sd-title") {
            h3 { + "Online" }
        }
        div(classes = "suggestions-list") { id = "online"
            onlines.forEach {
                div(classes = "suggestion-usd") {
                    a {
                        href = "/${it.username}"
                        title = it.username
                        img {
                            src = if (it.photo == "default") "/public/images/profile_default.jpg" else "/public/photos/${it.photo}"
                            alt = it.username
                            width = "35"
                            height = "35"
                        }
                    }
                    div(classes = "sgt-text") {
                        h4 { + it.username }
                        span {
                            i(classes = "fa fa-clock-o") {}
                            if (it.isOnline)  + " online" else  + " ${it.date.toString(DateTimeFormat.shortTime())}"
                        }
                    }
                    span {
                        if (it.isOnline) {
                            a {
                                href = "${currentUser.username}/chats/${it.username}"
                                title = "Chat"
                                i(classes = "la la-comments-o") {}
                            }
                        } else {
                            a {
                                href = "/${it.username}"
                                title = "Profil of ${it.username}"
                                i(classes = "la la-user") {}
                            }
                        }

                    }

                }
            }
        }
    }

    div(classes = "suggestionsth full-width") {
        div(classes = "sd-title") {
            h3 { + "Notifications" }
        }
        div(classes = "suggestions-list") { id = "notification" }
    }
}