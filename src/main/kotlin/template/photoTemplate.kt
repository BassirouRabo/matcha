package template

import kotlinx.html.*

fun DIV.photoTemplate(name : String) {
    div(classes = "col-lg-4 col-md-4 col-sm-6 col-6") {
        div(classes = "gallery_pt") {
            img {
                src = "public/photos/$name"
                width = "271"
                height = "174"
                alt = ""
            }
        }
    }

}