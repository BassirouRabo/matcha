package template

import kotlinx.html.*

fun UL.sidePhotoTemplate(name: String) {
    li {
        a {
            href = "#"
            title = ""
            img {
                src = "public/photos/$name"
                width = "70"
                height = "70"
            }
        }
    }
}