package pages

import data.Users
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import kotlinx.html.body
import kotlinx.html.*
import kotlinx.html.head
import kotlinx.html.title

suspend fun ApplicationCall.registerPage() {
    respondHtml {
       /* head {
            title {  + "Register pager" }
        }
        body {
            h1 { + "Register page" }
            form(locations.href(RegisterUrl()), encType = FormEncType.multipartFormData, method = FormMethod.post)  {
                div{
                    p{
                        textInput(name = Users.username.name) {
                            placeholder = "Username"
                            required = true
                        }
                    }
                    p {
                        emailInput ( name = Users.email.name ) {
                            placeholder = "email"
                            required = true
                        }
                    }
                    p {
                        textInput(name = Users.firstName.name) {
                            placeholder = "firstName"
                            required = true
                        }
                    }
                    p {
                        textInput(name = Users.lastName.name) {
                            placeholder = "lastName"
                            required = true
                        }
                    }
                    p {
                        + "Gender"
                        br
                        radioInput(name = Users.gender.name) {
                            value = "M"
                            checked = true
                            + "M"
                        }
                        br
                        radioInput(name = Users.gender.name) {
                            value = "F"
                            + "F"
                        }
                    }
                    p {
                        numberInput(name = Users.age.name) {
                            placeholder = "age"
                            min = "0"
                            max = "100"
                            required = true
                        }
                    }
                    p {
                        passwordInput(name = Users.password.name) {
                            placeholder = "password"
                            required = true
                        }
                    }
                    p {
                        fileInput(name = Users.photo.name) {
                            placeholder = "photo"
                        }
                    }
                    p {
                        textInput(name = Users.biography.name) {
                            placeholder = "Biography"
                        }
                    }
                    p {
                        submitInput { value = "Register"}
                    }
                }
            }

        }*/

        head {
            meta(charset = "UTF-8")
            title{ + "42 Date | Sign in" }
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
                href = "public/css/line-awesome.css"
            }
            link(rel = "stylesheet") {
                type = "text/css"
                href = "public/css/line-awesome-font-awesome.min.css"
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


        body(classes = "sign-in") {
            div(classes = "wrapper") {
                div(classes = "sign-in-page") {
                    div(classes = "signin-popup") {
                        div(classes = "signin-pop") {
                            div(classes = "row") {
                                div(classes = "col-lg-6") {
                                    div(classes = "cmp-info") {
                                        div(classes = "cm-logo") { }
                                        img(src = "public/images/logo_42_dating.png") {
                                            alt = ""
                                        }
                                    }
                                }
                                div(classes = "col-lg-6") {
                                    div(classes = "login-sec") {
                                        ul(classes = "sign-control") {
                                            li(classes = "current") {
                                                attributes["data-tab"] = "tab-1"
                                                a(href = "#") {
                                                    title = ""
                                                    + "Sign in"
                                                }
                                            }
                                            li() {
                                                attributes["data-tab"] = "tab-2"
                                                a(href = "#") {
                                                    title = ""
                                                    + "Sign up"
                                                }
                                            }
                                        }
                                        div(classes = "sign_in_sec current") {
                                            form() {
                                                div(classes = "row") {
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            textInput() {
                                                                name = Users.username.name
                                                                placeholder = "Username"
                                                            }
                                                            i(classes = "la la-user") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            passwordInput {
                                                                name = Users.password.name
                                                                placeholder = "Password"
                                                            }
                                                            i(classes = "la la-lock") {}
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        button () {
                                                            type = ButtonType.submit
                                                            value = "submit"
                                                            + "Sign in"
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        div(classes = "sign_in_sec") {
                                            id = "tab-2"
                                            div(classes = "dff-tab current") {
                                                id = "tab-3"
                                                form() {
                                                    div(classes = "row") {
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                textInput {
                                                                    name = Users.firstName.name
                                                                    placeholder = "First Name"
                                                                }
                                                                i(classes = "la la-user") {}
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                textInput {
                                                                    name = Users.lastName.name
                                                                    placeholder = "Last Name"
                                                                }
                                                                i(classes = "la la-user") {}
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                textInput {
                                                                    name = Users.username.name
                                                                    placeholder = "Username"
                                                                }
                                                                i(classes = "la la-user") {}
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                emailInput {
                                                                    name = Users.email.name
                                                                    placeholder = "Email"
                                                                }
                                                                i(classes = "la la-user") {}
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                textInput {
                                                                    name = "country"
                                                                    placeholder = "Country"
                                                                }
                                                                i(classes = "la la-globe") {}
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                select(classes = "") {
                                                                    option() {
                                                                        value = Gender.M.toString()
                                                                        + "M"
                                                                    }
                                                                    option() {
                                                                        value = Gender.F.toString()
                                                                        + "F"
                                                                    }
                                                                }
                                                                i(classes = "la la-globe") {}
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                passwordInput(classes = "") {
                                                                    name = "password"
                                                                    placeholder = "Password"
                                                                }
                                                                i(classes = "la la-lock") {}
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            div(classes = "sn-field") {
                                                                passwordInput(classes = "") {
                                                                    name = "repeat-password"
                                                                    placeholder = "Repeat Password"
                                                                }
                                                                i(classes = "la la-lock")
                                                            }
                                                        }
                                                        div(classes = "col-lg-12 no-pdd") {
                                                            button () {
                                                                type = ButtonType.submit
                                                                value = "submit"
                                                                + "Get Started"
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
                    div(classes = "footy-sec") {
                        div(classes = "container") {
                            ul{
                                li{
                                    a(href = "#") {
                                        title = ""
                                        + "School"
                                    }
                                }
                                li{
                                    a(href = "#") {
                                        title = ""
                                        + "Privacy Policy"
                                    }
                                }
                                li{
                                    a(href = "#") {
                                        title = ""
                                        + "Campus"
                                    }
                                }
                                li{
                                    a(href = "#") {
                                        title = ""
                                        + "Cookies Policy"
                                    }
                                }
                                li{
                                    a(href = "#") {
                                        title = ""
                                        + "Shop"
                                    }
                                }
                            }
                            p{
                                img(src = "public/images/copy-icon.png") {
                                    alt = ""
                                    + "Copyright @brabo-hi 2018"
                                }
                            }
                        }
                    }
                }
            }
            script(type = "text/javascript") { src = "public/js/jquery.min.js" }
            script(type = "text/javascript") { src = "public/js/popper.js" }
            script(type = "text/javascript") { src = "public/js/bootstrap.min.js" }
            script(type = "text/javascript") { src = "public/js/script.js" }
        }
    }
}