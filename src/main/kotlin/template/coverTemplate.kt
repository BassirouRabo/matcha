package template

import kotlinx.html.*

fun DIV.coverTemplate(type : String): Unit {
    section(classes = "cover-sec") {
        img {
            if (type.equals("user")) src = "public/photos/cover.png" else src = "public/photos/cover.jpg"
            alt = "Cover"
        }
        a() {
            href = "#"
            title = ""
            +"Change Image"
            i(classes = "fa fa-camera") {}
        }
    }
}