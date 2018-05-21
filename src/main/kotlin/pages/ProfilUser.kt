package pages

import io.ktor.application.ApplicationCall
import io.ktor.locations.Location
import io.ktor.request.uri
import io.ktor.response.respondText
import UserUrl
import data.User
import io.ktor.html.respondHtml
import kotlinx.html.*

suspend fun ApplicationCall.profilPage(user: User) {
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
                href = "public/css/animate.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/bootstrap.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/flatpickr.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/jquery.range.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/line-awesome.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/line-awesome-font-awesome.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/jquery.mCustomScrollbar.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/font-awesome.min.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/lib/slick/slick.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/lib/slick/slick-theme.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/style.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/responsive.css"
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
                                    img(src = "public/images/logo.png") {
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
                    a() {
                        href = "#"
                        title = ""
                        + "Change Image"
                        i(classes = "fa fa-camera") {}
                    }
                }

                //TODO change to main
                div {
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
                                                ul(classes = "social_links") {
                                                    li{
                                                        a() {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-facebook-square") {}
                                                            + "@brabo-hi"
                                                        }
                                                    }
                                                    li{
                                                        a() {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-twitter") {}
                                                            + "@brabo-hi"
                                                        }
                                                    }
                                                    li{
                                                        a() {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-instagram") {}
                                                            + "@brabo-hi"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-6") {
                                        div(classes = "main-ws-sec") {
                                            div(classes = "user-tab-sec") {
                                                h3 { + "Brabo-hi" }
                                                div (classes = "star-descp") {
                                                    span { + "Bassirou Rabo Hima" }
                                                }
                                                div(classes = "tab-feed st2") {
                                                    ul {
                                                        li(classes = "active") {
                                                            attributes["data-tab"] = "info-dd"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img() {
                                                                    src = "public/images/ic2.png"
                                                                    alt = ""
                                                                }
                                                                span { + "Info" }
                                                            }
                                                        }
                                                        li(classes = "") {
                                                            attributes["data-tab"] = "saved-jobs"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "public/images/ic4.png"
                                                                    alt = ""
                                                                }
                                                                span { + "Like 35.000" }
                                                            }
                                                        }
                                                        li(classes = "") {
                                                            attributes["data-tab"] = "my-bids"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "public/images/ic5.png"
                                                                    alt = ""
                                                                }
                                                                span { + "Liked 45.000" }
                                                            }
                                                        }
                                                        li(classes = "") {
                                                            attributes["data-tab"] = "portfolio-dd"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "public/images/ic3.png"
                                                                    alt = ""
                                                                }
                                                                span { + "Visits" }
                                                            }
                                                        }
                                                        li(classes = "") {
                                                            attributes["data-tab"] = "payment-dd"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "public/images/ic6.png"
                                                                    alt = ""
                                                                }
                                                                span { + "Visited" }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab current") {
                                                id = "info-dd"
                                                div(classes = "user-profile-ov") {
                                                    h3(classes = "") {
                                                        a(classes = "overview-open") {
                                                            href = "#"
                                                            title = ""
                                                            + "Overview"
                                                        }
                                                        a(classes = "overview-open") {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-pencil") { }
                                                        }
                                                    }
                                                    p { + "Zappy is an entirely automatic game where some computer programs play amongst them-selves. The game speed is defined by a time unit. Each action in the game has a durationproportionate to this time unit." }
                                                }
                                                div(classes = "user-profile-ov") {
                                                    h3 {
                                                        a(classes = "lct-box-open") {
                                                            href = "#"
                                                            title = ""
                                                            + "Location"
                                                        }
                                                        a(classes = "lct-box-open") {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-pencil") { }
                                                        }
                                                        a(classes = "") {
                                                            href = "#"
                                                            i(classes = "fa fa-plus-square") { }
                                                        }
                                                    }
                                                    h4 { + "Fremont" }
                                                    p { + "16600 Dumbarton Circle " }
                                                }
                                                div(classes = "user-profile-ov") {
                                                    h3 {
                                                        a(classes = "skills-open") {
                                                            href = "#"
                                                            title = ""
                                                            + "Skills"
                                                        }
                                                        a(classes = "skills-open") {
                                                            href = "#"
                                                            title = ""
                                                            i (classes = "fa fa-pencil") { }
                                                        }
                                                        a(classes = "") {
                                                            href = "#"
                                                            title = ""
                                                            i (classes = "fa fa-plus-square") { }
                                                        }
                                                    }
                                                    ul {
                                                        li {
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                + "HTML"
                                                            }
                                                        }
                                                        li {
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                + "PHP"
                                                            }
                                                        }
                                                        li {
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                + "CSS"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "saved-jobs"
                                                div(classes = "posts-section") {
                                                    div(classes = "post-bar") {
                                                        div(classes = "post_topbar") {
                                                            div(classes = "usy-dt") {
                                                                img {
                                                                    src = "http://via.placeholder.com/50x50"
                                                                    alt = ""
                                                                }
                                                                div(classes = "usy-name") {
                                                                    h3 { + "Brabo Hi" }
                                                                    span {
                                                                        img {
                                                                            src = "images/clock.png"
                                                                            alt = ""
                                                                        }
                                                                        + "3 min ago"
                                                                    }
                                                                }
                                                            }
                                                            div(classes = "ed-opts") {
                                                                a(classes = "ed-opts-open") {
                                                                    href = "#"
                                                                    i(classes = "la la-envelope")
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "my-bids"
                                                div(classes = "posts-section") {
                                                    div(classes = "post-bar") {
                                                        div(classes = "post_topbar") {
                                                            div(classes = "usy-dt") {
                                                                img {
                                                                    src = "http://via.placeholder.com/50x50"
                                                                    alt = ""
                                                                }
                                                                div(classes = "usy-name") {
                                                                    h3 { + "Brabo Hi" }
                                                                    span {
                                                                        img {
                                                                            src = "images/clock.png"
                                                                            alt = ""
                                                                        }
                                                                        + "3 min ago"
                                                                    }
                                                                }
                                                            }
                                                            div(classes = "ed-opts") {
                                                                a(classes = "ed-opts-open") {
                                                                    href = "#"
                                                                    i(classes = "la la-envelope")
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "portfolio-dd"
                                                div(classes = "posts-section") {
                                                    div(classes = "post-bar") {
                                                        div(classes = "post_topbar") {
                                                            div(classes = "usy-dt") {
                                                                img {
                                                                    src = "http://via.placeholder.com/50x50"
                                                                    alt = ""
                                                                }
                                                                div(classes = "usy-name") {
                                                                    h3 { + "Brabo Hi" }
                                                                    span {
                                                                        img {
                                                                            src = "images/clock.png"
                                                                            alt = ""
                                                                        }
                                                                        + "3 min ago"
                                                                    }
                                                                }
                                                            }
                                                            div(classes = "ed-opts") {
                                                                a(classes = "ed-opts-open") {
                                                                    href = "#"
                                                                    i(classes = "la la-envelope")
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "payment-dd"
                                                div(classes = "posts-section") {
                                                    div(classes = "post-bar") {
                                                        div(classes = "post_topbar") {
                                                            div(classes = "usy-dt") {
                                                                img {
                                                                    src = "http://via.placeholder.com/50x50"
                                                                    alt = ""
                                                                }
                                                                div(classes = "usy-name") {
                                                                    h3 { + "Brabo Hi" }
                                                                    span {
                                                                        img {
                                                                            src = "images/clock.png"
                                                                            alt = ""
                                                                        }
                                                                        + "3 min ago"
                                                                    }
                                                                }
                                                            }
                                                            div(classes = "ed-opts") {
                                                                a(classes = "ed-opts-open") {
                                                                    href = "#"
                                                                    i(classes = "la la-envelope")
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
                                            div(classes = "widget widget-portfolio") {
                                                div(classes = "wd-heady") {
                                                    h3 { + "My Photos" }
                                                    img {
                                                        src = "public/images/photo-icon.png"
                                                        alt = ""
                                                    }
                                                }
                                                div(classes = "pf-gallery") {
                                                    ul {
                                                        li {
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "http://via.placeholder.com/70x70"
                                                                    alt = ""
                                                                }
                                                            }
                                                        }
                                                        li {
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "http://via.placeholder.com/70x70"
                                                                    alt = ""
                                                                }
                                                            }
                                                        }
                                                        li {
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                img {
                                                                    src = "http://via.placeholder.com/70x70"
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
                                    src = "public/images/copy-icon2.png"
                                    alt = ""
                                }
                            }
                        }
                    }
                }

                div(classes = "overview-box") {
                    id = "overview-box"
                    div(classes = "overview-edit") {
                        h3 {
                            + "Overview"
                        }
                        span { + "5000 character left" }
                        form {
                            textArea(classes = "save") { + "Save" }
                            textArea(classes = "cancel") { + "Cancel" }
                        }
                        a(classes = "close-box") {
                            href = ""
                            title = ""
                            i(classes = "la la-close") { }
                        }
                    }
                }

            }

            script(type = "text/javascript") { src = "public/js/jquery.min.js" }
            script(type = "text/javascript") { src = "public/js/popper.js" }
            script(type = "text/javascript") { src = "public/js/bootstrap.min.js" }
            script(type = "text/javascript") { src = "public/js/jquery.mCustomScrollbar.js" }
            script(type = "text/javascript") { src = "public/js/jquery.range-min.js" }
            script(type = "text/javascript") { src = "public/js/flatpickr.min.js" }
            script(type = "text/javascript") { src = "public/lib/slick/slick.min.js" }
            script(type = "text/javascript") { src = "public/js/scrollbar.js" }
            script(type = "text/javascript") { src = "public/js/script.js" }
        }
    }
}