package template

import kotlinx.html.DIV
import kotlinx.html.div
import kotlinx.html.img

fun DIV.registerLeftTemplate(): Unit {
    div(classes = "col-lg-6") {
        div(classes = "cmp-info") {
            div(classes = "cm-logo") { }
            img(src = "/public/images/logo_42_dating.png") {
                alt = ""
            }
        }
    }
}