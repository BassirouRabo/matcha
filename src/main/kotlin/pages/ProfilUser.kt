package pages

import Campus
import Gender
import LoginUrl
import LogoutUrl
import Session
import UserUrl
import data.User
import data.Users
import getCampus
import getGender
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.*
import template.*

suspend fun ApplicationCall.profilPage(user: User, likes: List<User>, likeds: List<User>, visits: List<User>, visiteds: List<User>) {
    val username = sessions.get<Session>()!!.username

    respondHtml {
        head { headTemplate("Profil") }

        body {

            div(classes = "wrapper") {

                headerTemplate(username)

                coverTemplate()

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
                                                infoTemplete(user)
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-6") {
                                        div(classes = "main-ws-sec") {
                                            div(classes = "user-tab-sec") {
                                                h3 { +"Brabo-hi" }
                                                div(classes = "star-descp") {
                                                    span { +"Bassirou Rabo Hima" }
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
                                                                span { +"Info" }
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
                                                                span { +"Like ${likes.size}" }
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
                                                                span { +"Liked by ${likeds.size}" }
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
                                                                span { +"Visits ${visits.size}" }
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
                                                                span { +"Visited by ${visiteds.size}" }
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
                                                            +"About me"
                                                        }
                                                        a(classes = "overview-open") {
                                                            href = "#"
                                                            title = ""
                                                            i(classes = "fa fa-pencil") { }
                                                        }
                                                    }
                                                    p { +user.biography }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "like"
                                                div(classes = "posts-section") {
                                                    likes.forEach { user -> userTemplate(user) }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "liked"
                                                div(classes = "posts-section") {
                                                    likeds.forEach { user -> userTemplate(user) }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "visit"
                                                div(classes = "posts-section") {
                                                    visits.forEach { user -> userTemplate(user) }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "visited"
                                                div(classes = "posts-section") {
                                                    visiteds.forEach { user -> userTemplate(user) }
                                                }
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-3") {
                                        div(classes = "right-sidebar") {
                                            div(classes = "message-btn") {
                                                a(classes = "logout") {
                                                    href = locations.href(LogoutUrl())
                                                    title = "Sign out"
                                                    i(classes = "la la-sign-out") {}
                                                    +"Logout"
                                                }
                                            }
                                            div(classes = "widget widget-portfolio") {
                                                div(classes = "wd-heady") {
                                                    h3 { +"My Photos" }
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

                footerTemplate()

                div(classes = "overview-box") {
                    id = "overview-box"
                    div(classes = "overview-edit") {
                        h3 { +"Overview" }
                        span { +"5000 character left" }
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
                                +user.biography
                            }
                            passwordInput {
                                name = Users.password.name
                                placeholder = "Password"
                                required = true
                            }
                            button(classes = "save") {
                                type = ButtonType.submit
                                +"Save"
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

            scripTempate()
        }
    }
}