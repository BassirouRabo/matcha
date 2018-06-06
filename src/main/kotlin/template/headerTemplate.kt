package template

import kotlinx.html.*

fun DIV.headerTemplate(username: String): Unit {
    header {
        div(classes = "container") {
            div(classes = "header-data") {
                div(classes = "logo") {
                    a {
                        href = "/"
                        title = ""
                        img(src = "/public/images/logo.png") {
                            alt = ""
                        }
                    }
                }
                div(classes = "user-account") {
                    div(classes = "user-info") {
                        a {
                            href = "/$username"
                            title = username
                            i(classes = "la la-user-secret") {}
                            + " $username"
                        }
                    }
                }
            }
        }
    }
}