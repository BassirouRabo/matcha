package template

import data.User
import kotlinx.html.*

fun DIV.chatOutTemplate(message: String, user: User) {
    div(classes = "main-message-box st3") {
        div(classes = "message-dt st3") {
            div(classes = "message-inner-dt") {
                p { + message }
            }
            span { +"2 minutes ago" }
        }
        div(classes = "messg-usr-img") {
            img {
                if (user.photo.equals("default")) src = "/public/photos/50x50.png" else src = "/public/photos/${user.photo}"
                alt = user.username
                width = "50"
                height = "50"
            }
        }
    }
}