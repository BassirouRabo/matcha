package template

import data.User
import kotlinx.html.*

fun DIV.chatInTemplate(chat : data.Chat) {
    div(classes = "main-message-box ta-right") {
        div(classes = "message-dt") {
            div(classes = "message-inner-dt") {
                p { + chat.message }
            }
            span { +"Sat, Aug 23, 1:08 PM" }
        }
        div(classes = "messg-usr-img") {
            img {
                src = "/public/photos/50x50.png"
                alt = chat.username1
                width = "50"
                height = "50"
            }
        }
    }
}