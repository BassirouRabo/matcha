package template

import kotlinx.html.HEAD
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.title

fun HEAD.headTemplate(title: String): Unit {
    meta(charset = "UTF-8")
    title { +"42 Date | $title" }
    meta(name = "viewport") {
        content = "width=device-width, initial-scale=1.0"
    }
    meta(name = "description") {
        content = ""
    }
    meta(name = "keywords") {
        content = ""
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/animate.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/bootstrap.min.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/flatpickr.min.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/jquery.range.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/line-awesome.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/line-awesome-font-awesome.min.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/jquery.mCustomScrollbar.min.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/font-awesome.min.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/lib/slick/slick.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/lib/slick/slick-theme.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/style.css"
    }
    link(rel = "stylesheet") {
        type = "text/css"
        href = "/public/css/responsive.css"
    }
}