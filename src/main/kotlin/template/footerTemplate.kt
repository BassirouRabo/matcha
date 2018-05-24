package template

import kotlinx.html.*

fun DIV.footerTemplate(): Unit {
    footer {
        div(classes = "footy-sec mn no-margin") {
            div(classes = "container") {
                ul {
                    li {
                        a() {
                            href = "#"
                            title = ""
                            +"Intra"
                        }
                    }
                    li {
                        a() {
                            href = "#"
                            title = ""
                            +"School"
                        }
                    }
                    li {
                        a() {
                            href = "#"
                            title = ""
                            +"Privacy Policy"
                        }
                    }
                    li {
                        a() {
                            href = "#"
                            title = ""
                            +"Cookies Policy"
                        }
                    }
                    li {
                        a() {
                            href = "#"
                            title = ""
                            +"Shop"
                        }
                    }
                    li {
                        a() {
                            href = "#"
                            title = ""
                            +"Developed by @brabo-hi"
                        }
                    }
                }
                p {
                    +"Copyright 2018"
                    img {
                        src = "/public/images/copy-icon2.png"
                        alt = ""
                    }
                }
            }
        }
    }
}