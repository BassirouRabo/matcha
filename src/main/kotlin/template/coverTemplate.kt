package template

import kotlinx.html.*

fun DIV.coverTemplate(type : String): Unit {
    section(classes = "cover-sec") {
        img {
            src = if (type == "user") "public/photos/cover.jpg" else "public/photos/cover1.jpg"
            alt = "Cover"
        }
    }
}