package template

import data.User
import kotlinx.html.*

fun DIV.chatOutTemplate(chat : data.Chat) {
    div(classes = "main-message-box st3") {
        div(classes = "message-dt st3") {
            div(classes = "message-inner-dt") {
                p { + chat.message }
            }
            span { +"2 minutes ago" }
        }
        div(classes = "messg-usr-img") {
            img {
                src = "/public/photos/50x50.png"
                alt = chat.username2
                width = "50"
                height = "50"
            }
        }
    }
}