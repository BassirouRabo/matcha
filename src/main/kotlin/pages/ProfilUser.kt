package pages

import io.ktor.application.ApplicationCall
import io.ktor.locations.Location
import io.ktor.request.uri
import io.ktor.response.respondText
import UserUrl
import data.User
import data.Users
import getCampus
import getGender
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import kotlinx.html.*

suspend fun ApplicationCall.profilPage(user: User, likes: List<User>, likeds: List<User>, visits: List<User>, visiteds: List<User>) {
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

                section {
                    div(classes = "main-section") {
                        div(classes = "container") {
                            div(classes = "main-section-data") {
                                div(classes = "row") {
                                    div(classes = "col-lg-3") {
                                        div(classes = "main-left-sidebar") {
                                            div(classes = "user_profile") {
                                                div(classes = "user-pro-img") {
                                                    img {
                                                        src = "http://via.placeholder.com/170x170"
                                                        alt = ""
                                                    }
                                                    a {
                                                        href = "#"
                                                        i(classes = "fa fa-camera") {}
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
                                                            attributes["data-tab"] = "like"
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
                                                            attributes["data-tab"] = "liked"
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
                                                            attributes["data-tab"] = "visit"
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
                                                            attributes["data-tab"] = "visited"
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
                                                            + "About me"
                                                        }
                                                        a(classes = "overview-open") {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-pencil") { }
                                                        }
                                                    }
                                                    p { + user.biography }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "like"
                                                div(classes = "posts-section") {
                                                    likes.forEach { user ->
                                                        div(classes = "post-bar") {
                                                            div(classes = "post_topbar") {
                                                               a {
                                                                   href = locations.href(UserUrl(user.username))
                                                                   title = "Profil : ${user.username}"
                                                                   div(classes = "usy-dt") {
                                                                       img {
                                                                           src = "http://via.placeholder.com/50x50"
                                                                           alt = ""
                                                                       }
                                                                       div(classes = "usy-name") {
                                                                           h3 { + " ${user.username}" }
                                                                           span {
                                                                               img {
                                                                                   src = "images/clock.png"
                                                                                   alt = ""
                                                                               }
                                                                               + " ${user.firstName}"
                                                                           }
                                                                       }
                                                                   }
                                                               }
                                                                div(classes = "ed-opts") {
                                                                    ul(classes = "bk-links") {
                                                                        li {
                                                                            a {
                                                                                href = "#"
                                                                                i(classes = "la la-envelope") {}
                                                                            }
                                                                        }
                                                                        li{
                                                                            a(classes = "ed-opts-open") {
                                                                                href = "#"
                                                                                title = ""
                                                                                i(classes = "la la-ellipsis-v") {}
                                                                            }
                                                                            ul(classes = "ed-options") {
                                                                                li {
                                                                                    a {
                                                                                        href = "#"
                                                                                        title = ""
                                                                                        + "Block"
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
                                            div(classes = "product-feed-tab") {
                                                id = "liked"
                                                div(classes = "posts-section") {
                                                    likeds.forEach { user ->
                                                        div(classes = "post-bar") {
                                                            div(classes = "post_topbar") {
                                                                a {
                                                                    href = locations.href(UserUrl(user.username))
                                                                    title = "Profil : ${user.username}"
                                                                    div(classes = "usy-dt") {
                                                                        img {
                                                                            src = "http://via.placeholder.com/50x50"
                                                                            alt = ""
                                                                        }
                                                                        div(classes = "usy-name") {
                                                                            h3 { + " ${user.username}" }
                                                                            span {
                                                                                img {
                                                                                    src = "images/clock.png"
                                                                                    alt = ""
                                                                                }
                                                                                + " ${user.firstName}"
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                div(classes = "ed-opts") {
                                                                    ul(classes = "bk-links") {
                                                                        li {
                                                                            a {
                                                                                href = "#"
                                                                                i(classes = "la la-envelope") {}
                                                                            }
                                                                        }
                                                                        li{
                                                                            a(classes = "ed-opts-open") {
                                                                                href = "#"
                                                                                title = ""
                                                                                i(classes = "la la-ellipsis-v") {}
                                                                            }
                                                                            ul(classes = "ed-options") {
                                                                                li {
                                                                                    a {
                                                                                        href = "#"
                                                                                        title = ""
                                                                                        + "Block"
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
                                            div(classes = "product-feed-tab") {
                                                id = "visit"
                                                div(classes = "posts-section") {
                                                    visits.forEach { user ->
                                                        div(classes = "post-bar") {
                                                            div(classes = "post_topbar") {
                                                                a {
                                                                    href = locations.href(UserUrl(user.username))
                                                                    title = "Profil : ${user.username}"
                                                                    div(classes = "usy-dt") {
                                                                        img {
                                                                            src = "http://via.placeholder.com/50x50"
                                                                            alt = ""
                                                                        }
                                                                        div(classes = "usy-name") {
                                                                            h3 { + " ${user.username}" }
                                                                            span {
                                                                                img {
                                                                                    src = "images/clock.png"
                                                                                    alt = ""
                                                                                }
                                                                                + " ${user.firstName}"
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                div(classes = "ed-opts") {
                                                                    ul(classes = "bk-links") {
                                                                        li {
                                                                            a {
                                                                                href = "#"
                                                                                i(classes = "la la-envelope") {}
                                                                            }
                                                                        }
                                                                        li{
                                                                            a(classes = "ed-opts-open") {
                                                                                href = "#"
                                                                                title = ""
                                                                                i(classes = "la la-ellipsis-v") {}
                                                                            }
                                                                            ul(classes = "ed-options") {
                                                                                li {
                                                                                    a {
                                                                                        href = "#"
                                                                                        title = ""
                                                                                        + "Block"
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
                                            div(classes = "product-feed-tab") {
                                                id = "visited"
                                                div(classes = "posts-section") {
                                                    visiteds.forEach { user ->
                                                        div(classes = "post-bar") {
                                                            div(classes = "post_topbar") {
                                                                a {
                                                                    href = locations.href(UserUrl(user.username))
                                                                    title = "Profil : ${user.username}"
                                                                    div(classes = "usy-dt") {
                                                                        img {
                                                                            src = "http://via.placeholder.com/50x50"
                                                                            alt = ""
                                                                        }
                                                                        div(classes = "usy-name") {
                                                                            h3 { + " ${user.username}" }
                                                                            span {
                                                                                img {
                                                                                    src = "images/clock.png"
                                                                                    alt = ""
                                                                                }
                                                                                + " ${user.firstName}"
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                div(classes = "ed-opts") {
                                                                    ul(classes = "bk-links") {
                                                                        li {
                                                                            a {
                                                                                href = "#"
                                                                                i(classes = "la la-envelope") {}
                                                                            }
                                                                        }
                                                                        li{
                                                                            a(classes = "ed-opts-open") {
                                                                                href = "#"
                                                                                title = ""
                                                                                i(classes = "la la-ellipsis-v") {}
                                                                            }
                                                                            ul(classes = "ed-options") {
                                                                                li {
                                                                                    a {
                                                                                        href = "#"
                                                                                        title = ""
                                                                                        + "Block"
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
                        h3 { + "Overview" }
                        span { + "5000 character left" }
                        form {
                            textInput {
                                name = Users.username.name
                                value = user.username
                                readonly = true
                                required = true
                            }
                            textInput {
                                name = Users.firstName.name
                                value = user.firstName
                                required = true
                            }
                            textInput {
                                name = Users.lastName.name
                                value = user.lastName
                                required = true
                            }
                            emailInput {
                                name = Users.email.name
                                value = user.email
                                required = true
                            }
                            numberInput {
                                name = Users.age.name
                                value = user.age.toString()
                                min = "18"
                                max = "100"
                                required = true
                            }
                            select {
                                name = Users.campus.name
                                required = true
                                option {
                                    value = Campus.PARIS.toString()
                                    +"Paris"
                                }
                                option {
                                    value = Campus.FREMONT.toString()
                                    +"Fremont"
                                }
                            }
                            select {
                                name = Users.gender.name
                                required = true
                                option {
                                    value = Gender.MALE.toString()
                                    +"Male"
                                }
                                option {
                                    value = Gender.FEMALE.toString()
                                    +"Female"
                                }
                            }
                            textArea {
                                name = Users.biography.name
                                maxLength = "500"
                                required = true
                                + user.biography
                            }
                            passwordInput {
                                name = Users.password.name
                                placeholder = "Password"
                                required = true
                            }
                            button(classes = "save") {
                                type = ButtonType.submit
                                + "Save"
                            }
                        }
                        a(classes = "close-box") {
                            href = "#"
                            title = "close"
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