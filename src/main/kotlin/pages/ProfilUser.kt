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
import PhotoUrl
import getFriends

suspend fun ApplicationCall.profilPage(user: User, likes: List<User>, likeds: List<User>, visits: List<User>, visiteds: List<User>, bloques : List<User>, chats : List<User>) {
    val username = sessions.get<Session>()!!.username

    respondHtml {
        head { headTemplate("Profil") }

        body {

            div(classes = "wrapper") {

                headerTemplate(username)

                coverTemplate("profil")

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
                                                        if (user.photo.equals("default")) src = "public/photos/170x170.png" else src = "public/photos/${user.photo}"
                                                        alt = "Profil of ${user.username}"
                                                        width = "170"
                                                        height = "170"
                                                    }
                                                    a {
                                                        href = "#"
                                                        i(classes = "fa fa-camera") {}
                                                    }
                                                }
                                                infoTemplete(user)
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
                                                        if (!user.photo1.equals("default")) sidePhotoTemplate(user.photo1)
                                                        if (!user.photo2.equals("default")) sidePhotoTemplate(user.photo2)
                                                        if (!user.photo3.equals("default")) sidePhotoTemplate(user.photo3)
                                                        if (!user.photo4.equals("default")) sidePhotoTemplate(user.photo4)
                                                        if (!user.photo5.equals("default")) sidePhotoTemplate(user.photo5)
                                                        if (!user.photo6.equals("default")) sidePhotoTemplate(user.photo6)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-6") {
                                        div(classes = "main-ws-sec") {
                                            div(classes = "user-tab-sec") {
                                                h3 { + user.username }
                                                div(classes = "star-descp") {
                                                    span { + "${user.firstName} ${user.lastName}" }
                                                }
                                                div(classes = "tab-feed st2") {
                                                    ul {
                                                        li(classes = "active") {
                                                            attributes["data-tab"] = "info-dd"
                                                            a {
                                                                href = "#"
                                                                title = ""
                                                                span { + "Info" }
                                                            }
                                                        }
                                                        li {
                                                            attributes["data-tab"] = "like"
                                                            a {
                                                                href = "#"
                                                                title = "Like"
                                                                span { + "Like" }
                                                            }
                                                        }
                                                        li {
                                                            attributes["data-tab"] = "liked"
                                                            a {
                                                                href = "#"
                                                                title = "Liked"
                                                                span { + "Liked" }
                                                            }
                                                        }
                                                        li {
                                                            attributes["data-tab"] = "visit"
                                                            a {
                                                                href = "#"
                                                                title = "Visits"
                                                                span { + "Visits" }
                                                            }
                                                        }
                                                        li {
                                                            attributes["data-tab"] = "visited"
                                                            a {
                                                                href = "#"
                                                                title = "Visited"
                                                                span { + "Visited" }
                                                            }
                                                        }
                                                        li {
                                                            attributes["data-tab"] = "bloque"
                                                            a {
                                                                href = "#"
                                                                title = "Bloque"

                                                                span { +"Bloque" }
                                                            }
                                                        }
                                                        li {
                                                            attributes["data-tab"] = "chats"
                                                            a {
                                                                href = "#"
                                                                title = "Message"
                                                                span { + "Chat" }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab current") {
                                                id = "info-dd"
                                                div(classes = "user-profile-ov") {
                                                    h3 {
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
                                            div(classes = "product-feed-tab") {
                                                id = "bloque"
                                                div(classes = "posts-section") {
                                                    bloques.forEach { user -> userTemplate(user) }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "chats"
                                                div(classes = "posts-section") {
                                                    chats.forEach { user ->
                                                        div(classes = "post-bar") {
                                                            div(classes = "post_topbar") {
                                                                a {
                                                                    href = "$username/chats/${user.username}"
                                                                    title = "Profil : ${user.username}"
                                                                    div(classes = "usy-dt") {
                                                                        img {
                                                                            src = if (user.photo.equals("default")) "public/photos/170x170.png" else "public/photos/${user.photo}"
                                                                            alt = ""
                                                                            width = "50"
                                                                            height = "50"
                                                                        }
                                                                        div(classes = "usy-name") {
                                                                            h3 { +" ${user.username}" }
                                                                            span {
                                                                                +" ${user.firstName}"
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
                                        sideNotificationTemplate(user, getFriends(username))
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
                        h3 { +"About me" }
                        span { +"5000 character left" }
                        form(locations.href(PhotoUrl(user.username)), encType = FormEncType.multipartFormData, method = FormMethod.post) {
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
                                pattern = "[A-Za-z ]{1,15}"
                            }
                            textInput {
                                name = Users.lastName.name
                                value = user.lastName
                                required = true
                                pattern = "[A-Za-z ]{1,15}"
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
                            i(classes = "la la-camera-retro") {}
                            fileInput {
                                name = Users.photo.name
                                accept = "image/*"
                            }
                            i(classes = "la la-photo") { }
                            fileInput {
                                name = Users.photo1.name
                                accept = "image/*"
                            }
                            i(classes = "la la-photo") { }
                            fileInput {
                                name = Users.photo2.name
                                accept = "image/*"
                            }
                            i(classes = "la la-photo") { }
                            fileInput {
                                name = Users.photo3.name
                                accept = "image/*"
                            }
                            i(classes = "la la-photo") { }
                            fileInput {
                                name = Users.photo4.name
                                accept = "image/*"
                            }
                            i(classes = "la la-photo") { }
                            fileInput {
                                name = Users.photo5.name
                                accept = "image/*"
                            }
                            i(classes = "la la-photo") { }
                            fileInput {
                                name = Users.photo6.name
                                accept = "image/*"
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
            hiddenTemplate(username, username)
        }
    }
}