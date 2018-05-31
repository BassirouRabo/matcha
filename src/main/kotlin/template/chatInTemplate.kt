package template

import data.User
import kotlinx.html.*

fun DIV.chatInTemplate(message: String, user: User) {
    div(classes = "main-message-box ta-right") {
        div(classes = "message-dt") {
            div(classes = "message-inner-dt") {
                p { + message }
            }
            span { +"Sat, Aug 23, 1:08 PM" }
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