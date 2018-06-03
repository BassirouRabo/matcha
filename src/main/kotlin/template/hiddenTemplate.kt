package template

import kotlinx.html.BODY
import kotlinx.html.hiddenInput
import kotlinx.html.id

fun BODY.hiddenTemplate(username1: String, username2: String) {
    hiddenInput {
        id = "username1"
        value = username1
    }

    hiddenInput {
        id = "username2"
        value = username2
    }
}