package template

import kotlinx.html.*

fun DIV.coverTemplate(): Unit {
    section(classes = "cover-sec") {
        img {
            src = "http://via.placeholder.com/1600x400"
            alt = ""
        }
        a() {
            href = "#"
            title = ""
            +"Change Image"
            i(classes = "fa fa-camera") {}
        }
    }
}