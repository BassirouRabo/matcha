package template

import kotlinx.html.*

fun DIV.registerFootTemplate(): Unit {
    div(classes = "footy-sec") {
        div(classes = "container") {
            ul {
                li {
                    a(href = "#") {
                        title = ""
                        +"School"
                    }
                }
                li {
                    a(href = "#") {
                        title = ""
                        +"Privacy Policy"
                    }
                }
                li {
                    a(href = "#") {
                        title = ""
                        +"Campus"
                    }
                }
                li {
                    a(href = "#") {
                        title = ""
                        +"Cookies Policy"
                    }
                }
                li {
                    a(href = "#") {
                        title = ""
                        +"Shop"
                    }
                }
            }
            p {
                img(src = "/public/images/copy-icon.png") {
                    alt = ""
                    +"Copyright @brabo-hi 2018"
                }
            }
        }
    }
}