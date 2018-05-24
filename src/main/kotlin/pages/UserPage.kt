import io.ktor.application.ApplicationCall
import io.ktor.locations.Location
import io.ktor.request.uri
import io.ktor.response.respondText
import UserUrl
import data.User
import io.ktor.html.respondHtml
import kotlinx.html.*

suspend fun ApplicationCall.userPage(user: User) {
    respondHtml {
        head {
            meta(charset = "UTF-8")
            title{ + "42 Date | Home" }
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

        body {

            div(classes = "wrapper") {

                header {
                    div(classes = "container") {
                        div(classes = "header-data") {
                            div(classes = "logo") {
                                a(href = "index.html") {
                                    title = ""
                                    img(src = "/public/images/logo.png") {
                                        alt = ""
                                    }
                                }
                            }
                            div(classes = "user-account") {
                                div(classes = "user-info") {
                                    img(src = "http://via.placeholder.com/30x30") {
                                        alt = ""
                                    }
                                    a(href = "#") {
                                        title = ""
                                        + "Brabo"
                                    }
                                    i(classes = "la la-sort-down") {}
                                }
                                div(classes = "user-account-settingss") {
                                    h3{ + "Online Status" }
                                    ul(classes = "on-off-status") {
                                        li{
                                            div(classes = "fgt-sec") {
                                                radioInput(name = "cc") {
                                                    id = "c5"
                                                }
                                                label() {
                                                    htmlFor = "c5"
                                                    span {  }
                                                }
                                                small { + "Online" }
                                            }
                                        }
                                        li {
                                            div(classes = "fgt-sec") {
                                                radioInput(name = "cc") {
                                                    id = "c6"
                                                }
                                                label {
                                                    htmlFor = "c6"
                                                    span {  }
                                                }
                                                small { + "Offline" }
                                            }
                                        }
                                    }
                                    h3 { + "Setting" }
                                    ul(classes = "us-links") {
                                        li{
                                            a(href = "#") {
                                                title = ""
                                                + "Account Setting"
                                            }
                                            a(href = "#") {
                                                title = ""
                                                + "Privacy"
                                            }
                                            a(href = "#") {
                                                title = ""
                                                + "Faqs"
                                            }
                                            a(href = "#") {
                                                title = ""
                                                + "Terms & Conditions"
                                            }
                                        }
                                    }
                                    h3(classes = "tc") {
                                        a(href = "#") {
                                            title = ""
                                            + "Logout"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                section(classes = "cover-sec") {
                    img{
                        src = "http://via.placeholder.com/1600x400"
                        alt = ""
                    }
                }

                section {
                    div(classes = "main-section") {
                        div(classes = "container") {
                            div(classes = "main-section-data") {
                                div(classes = "row") {
                                    div(classes = "col-lg-3") {
                                        div(classes = "main-left-sidebar") {
                                            div(classes = "user_profile") {
                                                div(classes = "user-pro-img") {
                                                    img() {
                                                        src = "http://via.placeholder.com/170x170"
                                                        alt = ""
                                                    }
                                                }
                                                div(classes = "user_pro_status") {
                                                    ul(classes = "flw-hr") {
                                                        li {
                                                            a(classes = "flww") {
                                                                href = "#"
                                                                title = ""
                                                                i(classes = "la la-plus")
                                                                + "Follow"
                                                            }
                                                        }
                                                    }
                                                }
                                                ul(classes = "social_links") {
                                                    li{
                                                        i(classes = "fa fa-instagram") {}
                                                        + " ${user.username}"
                                                    }
                                                    li{
                                                        i(classes = "fa fa-instagram") {}
                                                        + " ${user.firstName}"
                                                    }
                                                    li{
                                                        i(classes = "fa fa-instagram") {}
                                                        + " ${user.lastName}"
                                                    }
                                                    li{
                                                        i(classes = "fa fa-instagram") {}
                                                        + " ${getGender(user.gender)}"
                                                    }
                                                    li{
                                                        i(classes = "fa fa-instagram") {}
                                                        + " ${getCampus(user.campus)}"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-6") {
                                        div(classes = "main-ws-sec") {
                                            div(classes = "user-tab-sec") {
                                                h3 { + "Brabo Hi" }
                                                div(classes = "star-descp") {
                                                    span { + "Bassirou Rabo Hima" }
                                                }
                                                div(classes = "tab-feed") {
                                                    ul {
                                                        li(classes = "active") {
                                                            attributes["data-tab"] = "info-dd"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "images/ic2.png"
                                                                    alt = ""
                                                                }
                                                                span { + "Info" }
                                                            }
                                                        }
                                                        li(classes = "") {
                                                            attributes["data-tab"] = "portfolio-dd"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "images/ic3.png"
                                                                    alt = ""
                                                                }
                                                                span { + "Photos" }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab current") {
                                                id = "info-dd"
                                                div(classes = "user-profile-ov") {
                                                    h3 { + "About me" }
                                                    p { + user.biography }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "portfolio-dd"
                                                div(classes = "portfolio-gallery-sec") {
                                                    div(classes = "gallery_pf") {
                                                        div(classes = "row") {
                                                            div(classes = "col-lg-4 col-md-4 col-sm-6 col-6") {
                                                                div(classes = "gallery_pt") {
                                                                    img {
                                                                        src = "http://via.placeholder.com/271x174"
                                                                        alt = ""
                                                                    }
                                                                    a {
                                                                        href = "#"
                                                                        title = ""
                                                                        img {
                                                                            src = "images/all-out.png"
                                                                            alt = ""
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            div(classes = "col-lg-4 col-md-4 col-sm-6 col-6") {
                                                                div(classes = "gallery_pt") {
                                                                    img {
                                                                        src = "http://via.placeholder.com/271x174"
                                                                        alt = ""
                                                                    }
                                                                    a {
                                                                        href = "#"
                                                                        title = ""
                                                                        img {
                                                                            src = "images/all-out.png"
                                                                            alt = ""
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            div(classes = "col-lg-4 col-md-4 col-sm-6 col-6") {
                                                                div(classes = "gallery_pt") {
                                                                    img {
                                                                        src = "http://via.placeholder.com/271x174"
                                                                        alt = ""
                                                                    }
                                                                    a {
                                                                        href = "#"
                                                                        title = ""
                                                                        img {
                                                                            src = "images/all-out.png"
                                                                            alt = ""
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-3") {
                                        div(classes = "right-sidebar") {
                                            div(classes = "message-btn") {
                                                a {
                                                    href = "#"
                                                    i(classes = "fa fa-envelope")
                                                    + "Message"
                                                }
                                            }
                                            div(classes = "suggestions full-width") {
                                                div(classes = "sd-title") {
                                                    h3 { + "Best match" }
                                                }
                                                div(classes = "suggestions-list") {
                                                    div(classes = "suggestion-usd") {
                                                        img {
                                                            src = "http://via.placeholder.com/35x35"
                                                            alt = ""
                                                        }
                                                        div(classes = "sgt-text") {
                                                            h4 { + "Brabo Hi" }
                                                            span { + "Bassirou Rabo Hima" }
                                                        }
                                                        span {
                                                            i(classes = "la la-plus")
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                footer {
                    div(classes = "footy-sec mn no-margin") {
                        div(classes = "container") {
                            ul {
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        + "Intra"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        + "School"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        + "Privacy Policy"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        + "Cookies Policy"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        + "Shop"
                                    }
                                }
                                li {
                                    a() {
                                        href = "#"
                                        title = ""
                                        + "Developed by @brabo-hi"
                                    }
                                }
                            }
                            p{
                                + "Copyright 2018"
                                img {
                                    src = "/public/images/copy-icon2.png"
                                    alt = ""
                                }
                            }
                        }
                    }
                }

            }

            script(type = "text/javascript") { src = "/public/js/jquery.min.js" }
            script(type = "text/javascript") { src = "/public/js/popper.js" }
            script(type = "text/javascript") { src = "/public/js/bootstrap.min.js" }
            script(type = "text/javascript") { src = "/public/js/jquery.mCustomScrollbar.js" }
            script(type = "text/javascript") { src = "/public/js/jquery.range-min.js" }
            script(type = "text/javascript") { src = "/public/js/flatpickr.min.js" }
            script(type = "text/javascript") { src = "/public/lib/slick/slick.min.js" }
            script(type = "text/javascript") { src = "/public/js/scrollbar.js" }
            script(type = "text/javascript") { src = "/public/js/script.js" }
        }
    }
}