package template

import data.User
import kotlinx.html.*
import org.joda.time.format.DateTimeFormat

fun DIV.chatInTemplate(chat : data.Chat) {
    div(classes = "main-message-box ta-right") {
        div(classes = "message-dt") {
            div(classes = "message-inner-dt") {
                p { + chat.message }
            }
            span { + chat.date.toString(DateTimeFormat.shortDateTime()) }
        }
    }
}