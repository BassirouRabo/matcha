package pages

import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.html.respondHtmlTemplate
import io.ktor.request.uri
import io.ktor.sessions.sessions
import kotlinx.html.*
import template.HomeTemplate
import Session
import io.ktor.locations.locations
import LogoutUrl
import data.User
import io.ktor.response.respondRedirect
import io.ktor.sessions.get
import org.jetbrains.exposed.sql.transactions.transaction
import repository.UserRepository
import UserUrl

suspend fun ApplicationCall.homePage() {
   /* var users: List<User> = listOf()
    transaction {
       users = UserRepository.getAll()
    }*/
    /*respondHtml {
        head {
            title("Home Page")
        }
        body {
            h1{
                + " HOME PAGE  ${request.uri}  "
            }

            p{
                + "Welcome "
                b { + "${sessions.get<Session>()?.username}"}
            }
            p{
                a(href = application.locations.href(LogoutUrl())) {
                    + "Logout"
                }
            }
            users.forEach {user ->
                p{
                    a(href = application.locations.href(UserUrl(user.username))) {
                        + user.username
                    }
                }
            }
        }

    }*/

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

            div(classes = "search-sec") {}

            //TODO to change to main
            div() {
                div(classes = "main-section") {
                    div(classes = "container") {
                        div(classes = "main-section-data") {
                            div(classes = "row") {
                                div(classes = "col-lg-3") {
                                    div(classes = "") {
                                        div(classes = "filter-secs full-width") {
                                            div(classes = "filter-heading") {
                                                h3 { + "Filters" }
                                                a(classes = "") {
                                                    href = "#"
                                                    title = ""
                                                    + "Clear all filters"
                                                }
                                            }
                                            div(classes = "paddy") {
                                                div(classes = "filter-dd") {
                                                    form {
                                                        textInput() {
                                                            name = "search-skills"
                                                            placeholder = "Search skills"
                                                        }
                                                    }
                                                }
                                                div(classes = "filter-dd") {
                                                    div(classes = "filter-ttl") {
                                                        h3 { + "Campus" }
                                                    }
                                                    ul(classes = "avail-checks") {
                                                        li {
                                                            radioInput() {
                                                                name = "cc"
                                                                id = "c1"
                                                            }
                                                            label {
                                                                htmlFor = "c1"
                                                                span {  }
                                                            }
                                                            small { + "Paris" }
                                                        }
                                                        li {
                                                            radioInput {
                                                                name = "cc"
                                                                id = "c2"
                                                            }
                                                            label {
                                                                htmlFor = "c2"
                                                                span {  }
                                                            }
                                                            small { + "USA" }
                                                        }
                                                    }
                                                }
                                                div(classes = "filter-dd") {
                                                    div(classes = "filter-ttl") {
                                                        h3 { + "Gender" }
                                                    }
                                                    ul(classes = "avail-checks") {
                                                        li {
                                                            radioInput {
                                                                name = "cc"
                                                                id = "c3"
                                                            }
                                                            label {
                                                                htmlFor = "c3"
                                                                span {  }
                                                            }
                                                            small { + "Male" }
                                                        }
                                                        li {
                                                            radioInput {
                                                                name = "cc"
                                                                id = "c4"
                                                            }
                                                            label {
                                                                htmlFor = "c4"
                                                                span {  }
                                                            }
                                                            small { + "Female" }
                                                        }
                                                    }
                                                }
                                                div(classes = "filter-dd") {
                                                    div(classes = "filter-ttl") {
                                                        h3 { + "Filter" }
                                                    }
                                                    form(classes = "job-tp") {
                                                        select {
                                                            option() { + "Filer 1"}
                                                            option() { + "Filer 2"}
                                                        }
                                                        i(classes = "fa fa-ellipsis-v") {
                                                            attributes["aria-hidden"] = "true"
                                                        }
                                                    }
                                                }
                                                div(classes = "filter-dd") {
                                                    div(classes = "filter-ttl") {
                                                        h3 { + "Age" }
                                                    }
                                                    div(classes = "rg-slider") {
                                                        hiddenInput(classes = "rn-slider slider-input") {
                                                            value = "5,50"
                                                        }
                                                    }
                                                    div(classes = "rg-limit") {
                                                        h4 { + "1" }
                                                        h4 { + "100" }
                                                    }
                                                }
                                                div(classes = "filter-dd") {
                                                    div(classes = "filter-ttl") {
                                                        h3 { + "Experience Level" }
                                                    }
                                                    form(classes = "job-tp") {
                                                        select {
                                                            option() { + "Select a experience level" }
                                                            option() { + "3 years" }
                                                        }
                                                        i(classes = "fa fa-ellipsis-v") {
                                                            attributes["aria-hidden"] = "true"
                                                        }
                                                    }
                                                }
                                                div(classes = "filter-dd") {
                                                    div(classes = "filter-ttl") {
                                                        h3 { + "Countries" }
                                                    }
                                                    form(classes = "job-tp") {
                                                        select {
                                                            option { + "Select a country"}
                                                            option { + "Paris" }
                                                            option { + "Fremont" }
                                                        }
                                                        i(classes = "fa fa-ellipsis-v") {
                                                            attributes["aria-hidden"] = "true"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        div(classes = "tags-sec full-width") {
                                            ul {
                                                li {
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        + "Intra"
                                                    }
                                                }
                                                li {
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        + "School"
                                                    }
                                                }
                                                li {
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        + "Privacy"
                                                    }
                                                }
                                                li {
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        + "Campus"
                                                    }
                                                }
                                                li {
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        + "Cookies"
                                                    }
                                                }
                                                li {
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        + "Shop"
                                                    }
                                                }
                                                li {
                                                    a(classes = "") {
                                                        href = "#"
                                                        title = ""
                                                        + "Language"
                                                    }
                                                }
                                                li {
                                                    a(classes = "Language") {
                                                        href = "#"
                                                        title = ""
                                                        + "Developed by @brabo-hi"
                                                    }
                                                }
                                            }
                                            div(classes = "cp-sec") {
                                                p{
                                                    img(classes = "") {
                                                        alt = ""
                                                        src = "public/images/cp.png"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                div(classes = "col-lg-6") {
                                    div(classes = "main-ws-sec") {
                                        div(classes = "posts-section") {
                                            div(classes = "top-profiles") {
                                                div(classes = "pf-hd") {
                                                    h3 { + "Top Match" }
                                                    i(classes = "la la-ellipsis-v") {}
                                                }
                                                div(classes = "profiles-slider") {
                                                    div(classes = "user-profy") {
                                                        img {
                                                            alt = ""
                                                            src = "http://via.placeholder.com/57x57"
                                                        }
                                                        h3 { + "Brabo-hi" }
                                                        span { + "Bassirou Rabo" }
                                                        ul {
                                                            li {
                                                                a(classes = "followw") {
                                                                    href = "#"
                                                                    title = ""
                                                                    + "Follow"
                                                                }
                                                            }
                                                        }
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            + "View Profile"
                                                        }
                                                    }
                                                    div(classes = "user-profy") {
                                                        img {
                                                            alt = ""
                                                            src = "http://via.placeholder.com/57x57"
                                                        }
                                                        h3 { + "Brabo-hi" }
                                                        span { + "Bassirou Rabo" }
                                                        ul {
                                                            li {
                                                                a(classes = "followw") {
                                                                    href = "#"
                                                                    title = ""
                                                                    + "Follow"
                                                                }
                                                            }
                                                        }
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            + "View Profile"
                                                        }
                                                    }
                                                    div(classes = "user-profy") {
                                                        img {
                                                            alt = ""
                                                            src = "http://via.placeholder.com/57x57"
                                                        }
                                                        h3 { + "Brabo-hi" }
                                                        span { + "Bassirou Rabo" }
                                                        ul {
                                                            li {
                                                                a(classes = "followw") {
                                                                    href = "#"
                                                                    title = ""
                                                                    + "Follow"
                                                                }
                                                            }
                                                        }
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            + "View Profile"
                                                        }
                                                    }
                                                    div(classes = "user-profy") {
                                                        img {
                                                            alt = ""
                                                            src = "http://via.placeholder.com/57x57"
                                                        }
                                                        h3 { + "Brabo-hi" }
                                                        span { + "Bassirou Rabo" }
                                                        ul {
                                                            li {
                                                                a(classes = "followw") {
                                                                    href = "#"
                                                                    title = ""
                                                                    + "Follow"
                                                                }
                                                            }
                                                        }
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            + "View Profile"
                                                        }
                                                    }
                                                    div(classes = "user-profy") {
                                                        img {
                                                            alt = ""
                                                            src = "http://via.placeholder.com/57x57"
                                                        }
                                                        h3 { + "Brabo-hi" }
                                                        span { + "Bassirou Rabo" }
                                                        ul {
                                                            li {
                                                                a(classes = "followw") {
                                                                    href = "#"
                                                                    title = ""
                                                                    + "Follow"
                                                                }
                                                            }
                                                        }
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            + "View Profile"
                                                        }
                                                    }
                                                    div(classes = "user-profy") {
                                                        img {
                                                            alt = ""
                                                            src = "http://via.placeholder.com/57x57"
                                                        }
                                                        h3 { + "Brabo-hi" }
                                                        span { + "Bassirou Rabo" }
                                                        ul {
                                                            li {
                                                                a(classes = "followw") {
                                                                    href = "#"
                                                                    title = ""
                                                                    + "Follow"
                                                                }
                                                            }
                                                        }
                                                        a {
                                                            href = "#"
                                                            title = ""
                                                            + "View Profile"
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "post-bar") {
                                                div(classes = "post_topbar") {
                                                    div(classes = "usy-dt") {
                                                        img() {
                                                            src = "http://via.placeholder.com/50x50"
                                                            alt = ""
                                                        }
                                                        div(classes = "usy-name") {
                                                            h3 { + "brabo -hi" }
                                                            span {
                                                                img() {
                                                                    src = "public/images/clock.png"
                                                                    alt = ""
                                                                }
                                                                + "3 min ago"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "ed-opts") {
                                                        a(classes = "ed-opts-open") {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "la la-ellipsis-v") {}
                                                        }
                                                        ul(classes = "ed-options") {
                                                            li{
                                                                a {
                                                                    href = "#"
                                                                    + "Block"
                                                                }
                                                            }
                                                            li{
                                                                a {
                                                                    href = "#"
                                                                    + "close"
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                div(classes = "epi-sec") {
                                                    ul(classes = "descp") {
                                                        li{
                                                            img {
                                                                src = "public/images/icon9.png"
                                                            }
                                                            span { + "Campus Paris" }
                                                        }
                                                    }
                                                    ul(classes = "bk-links") {
                                                        li {
                                                            a {
                                                                href = "#"
                                                                i(classes = "la la-bookmark") {}
                                                            }
                                                        }
                                                        li {
                                                            a {
                                                                href = "#"
                                                                i(classes = "la la-envelope") {}
                                                            }
                                                        }
                                                    }
                                                }
                                                div(classes = "job_descp") {
                                                    p { + "Zappy is an entirely automatic game where some computer programs play amongst them-  selves. The game speed is defined by a time unit. Each action in the game has a duration proportionate to this time unit" }
                                                }
                                            }
                                        }
                                    }
                                }
                                div(classes = "col-lg-3") {
                                    div(classes = "suggestionsth full-width") {
                                        div(classes = "sd-title") {
                                            h3 { + "Online" }
                                        }
                                        div(classes = "suggestions-list") {
                                            div(classes = "suggestion-usd") {
                                                img() {
                                                    alt = ""
                                                    src = "http://via.placeholder.com/35x35"
                                                }
                                                div(classes = "sgt-text") {
                                                    h4 { + "brabo-hi" }
                                                    span { + "Bassirou Rabo" }
                                                }
                                                span {
                                                    i(classes = "la la-plus") {}
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

            div(classes = "chatbox-list") {
                div(classes = "chatbox") {
                    div(classes = "chat-mg") {
                        a(href = "#") {
                            title = ""
                            img(src = "http://via.placeholder.com/70x70") {
                                alt = ""
                            }
                        }
                        span { + "2" }
                    }
                    div(classes = "conversation-box") {
                        div(classes = "con-title mg-3") {
                            div(classes = "chat-user-info") {
                                img(src = "http://via.placeholder.com/34x33") {
                                    alt = ""
                                }
                                h3 {
                                    + "Brabo Hi"
                                    span(classes = "status-info") {}
                                }
                            }
                            div(classes = "st-icons") {
                                a() {
                                    href = "#"
                                    title = ""
                                    i(classes = "la la-cog") { }
                                }
                                a(classes = "close-chat") {
                                    href = "#"
                                    title = ""
                                    i(classes = "la la-minus-square") {}
                                }
                                a(classes = "close-chat") {
                                    href = "#"
                                    title = ""
                                    i(classes = "la la-close") {}
                                }
                            }
                        }
                        div(classes = "chat-hist mCustomScrollbar") {
                            attributes["data-mcs-theme"] = "dark"
                            div(classes = "chat-msg") {
                                p { + "Zappy is an entirely automatic game where some computer programs play amongst them- selves. The game speed is defined by a time unit. Each action in the game has a duration" }
                                span { + "Sat, Aug 23, 1:10 PM" }
                            }
                            div(classes = "date-nd") {
                                span { + "Sunday, August 24" }
                            }
                            div(classes = "chat-msg st2") {
                                p { + "Un petit message ici" }
                                span { + "5 minutes ago" }
                            }
                            div(classes = "chat-msg") {
                                p { + "Zappy is an entirely automatic game where some computer programs play amongst them- selves. The game speed is defined by a time unit. Each action in the game has a duration" }
                                span { + "Sat, Aug 23, 1:10 PM" }
                            }
                        }
                        div(classes = "typing-msg") {
                            form() {
                                textArea() {
                                    placeholder = "Type a message here"
                                }
                                buttonInput {
                                    i(classes = "fa fa-send") {}
                                }
                            }
                            ul(classes = "ft-options") {
                                li(classes = "") {
                                    a(classes = "") {
                                        href = "#"
                                        title = ""
                                        i(classes = "la la-smile-o") { }
                                        i(classes = "la la-camera") { }
                                        i(classes = "fa fa-paperclip") { }
                                    }
                                }
                            }
                        }
                    }
                }
                div(classes = "chatbox") {
                    div(classes = "chat-mg bx") {
                        a(classes = "") {
                            href = "#"
                            title = ""
                            img(alt = "") {
                                src = "public/images/chat.png"
                            }
                        }
                        span { + "2" }
                    }
                    div(classes = "conversation-box") {
                        div(classes = "con-title") {
                            h3 { + "Messages" }
                            a(classes = "close-chat") {
                                href = "#"
                                i(classes = "la la-minus-square") { }
                            }
                        }
                        div(classes = "chat-list") {
                            div(classes = "conv-list active") {
                                div(classes = "usrr-pic") {
                                    img(alt = "") {
                                        src = "http://via.placeholder.com/50x50"
                                    }
                                    span(classes = "active-status activee") {  }
                                }
                                div(classes = "usy-info") {
                                    h3 { + "Brabo Hi" }
                                    span { + "Un petit message" }
                                }
                                div(classes = "ct-time") {
                                    span { + "1:55 PM" }
                                }
                                span(classes = "msg-numbers") { + "2" }
                            }
                            div(classes = "conv-list") {
                                div(classes = "usrr-pic") {
                                    img(alt = "") {
                                        src = "http://via.placeholder.com/50x50"
                                    }
                                }
                                div(classes = "usy-info") {
                                    h3 { + "Brabo Hi" }
                                    span { + "Un petit message" }
                                }
                                div(classes = "ct-time") {
                                    span { + "11:39 PM" }
                                }
                            }
                            div(classes = "conv-list") {
                                div(classes = "usrr-pic") {
                                    img(alt = "") {
                                        src = "http://via.placeholder.com/50x50"
                                    }
                                }
                                div(classes = "usy-info") {
                                    h3 { + "Brabo Hi" }
                                    span { + "Un petit message" }
                                }
                                div(classes = "ct-time") {
                                    span { + "0.28 AM" }
                                }
                            }
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
