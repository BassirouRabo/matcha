package template

import kotlinx.html.*
import org.joda.time.format.DateTimeFormat

fun DIV.chatOutTemplate(chat : data.Chat) {
    div(classes = "main-message-box st3") {
        div(classes = "message-dt st3") {
            div(classes = "message-inner-dt") {
                p { + chat.message }
            }
            span { + chat.date.toString(DateTimeFormat.shortDateTime()) }
        }
    }
}