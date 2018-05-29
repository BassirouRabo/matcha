import data.Bloque
import data.User
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import repository.BloqueRepository
import repository.LikeRepository
import template.*

suspend fun ApplicationCall.userPage(user: User) {

    val username = sessions.get<Session>()!!.username

    respondHtml {
        head { headTemplate(user.username) }

        body {

            div(classes = "wrapper") {

                headerTemplate(username)

                coverTemplate("user")

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
                                                        if (user.photo.equals("default"))
                                                            src = "public/photos/170x170.png"
                                                        else
                                                            src = "public/photos/${user.photo}"
                                                        alt = "Profil of ${user.username}"
                                                        width = "170"
                                                        height = "170"
                                                    }
                                                }
                                                if (user.photo.equals("default")
                                                        && user.photo1.equals("default")
                                                        && user.photo2.equals("default")
                                                        && user.photo3.equals("default")
                                                        && user.photo4.equals("default")
                                                        && user.photo5.equals("default")
                                                        && user.photo6.equals("default")) {
                                                    div(classes = "user_pro_status") {
                                                        ul(classes = "flw-hr") {
                                                            li {
                                                                transaction {
                                                                    val matching = LikeRepository.getMatch(username, user.username)
                                                                    if (matching == Matching.A1B) {
                                                                        a(classes = "pending") {
                                                                            href = locations.href(UnLikeUrl(user.username))
                                                                            title = "Unlike"
                                                                            i(classes = "la la-times-circle") {}
                                                                            +"pending"
                                                                        }
                                                                    } else if (matching == Matching.A2B) {
                                                                        a(classes = "like") {
                                                                            href = locations.href(UnLikeUrl(user.username))
                                                                            title = "Unlike"
                                                                            i(classes = "la la-users") {}
                                                                            +"Unlike"
                                                                        }
                                                                        a(classes = "like") {
                                                                            href = "#"
                                                                            title = "Message"
                                                                            i(classes = "fa fa-envelope")
                                                                            +"Message"
                                                                        }
                                                                    } else {
                                                                        a(classes = "unlike") {
                                                                            href = locations.href(LikeUrl(user.username))
                                                                            title = "like"
                                                                            i(classes = "la la-plus") {}
                                                                            +"Like"
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        ul(classes = "flw-hr") {
                                                            var bloque : Bloque? = null
                                                            transaction {
                                                                bloque  = BloqueRepository.get(username, user.username)
                                                            }
                                                            if (bloque == null) {
                                                                li {
                                                                    a(classes = "like") {
                                                                        href = locations.href(BloqueUrl(user.username))
                                                                        title = "Block"
                                                                        i(classes = "la la-user-times") {}
                                                                        +"Block"
                                                                    }
                                                                }
                                                            } else {
                                                                li {
                                                                    a(classes = "logout") {
                                                                        href = locations.href(UnbloqueUrl(user.username))
                                                                        title = "Block"
                                                                        i(classes = "la la-user-times") {}
                                                                        +"Unblock"
                                                                    }
                                                                }
                                                            }
                                                        }

                                                        ul(classes = "flw-hr") {
                                                            li {
                                                                a(classes = "logout") {
                                                                    href = locations.href(ReportUrl(user.username))
                                                                    title = "Report"
                                                                    i(classes = "la la-user-times") {}
                                                                    +"Report fake acount"
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                infoTemplete(user)
                                            }
                                        }
                                    }
                                    div(classes = "col-lg-9") {
                                        div(classes = "main-ws-sec") {
                                            div(classes = "user-tab-sec") {
                                                h3 { +"Brabo Hi" }
                                                div(classes = "star-descp") {
                                                    span { +"Bassirou Rabo Hima" }
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
                                                                span { +"Info" }
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
                                                                span { +"Photos" }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            div(classes = "product-feed-tab current") {
                                                id = "info-dd"
                                                div(classes = "user-profile-ov") {
                                                    h3 { +"About me" }
                                                    p { +user.biography }
                                                }
                                            }
                                            div(classes = "product-feed-tab") {
                                                id = "portfolio-dd"
                                                div(classes = "portfolio-gallery-sec") {
                                                    div(classes = "gallery_pf") {
                                                        div(classes = "row") {
                                                            if (!user.photo1.equals("default")) photoTemplate(user.photo1)
                                                            if (!user.photo2.equals("default")) photoTemplate(user.photo2)
                                                            if (!user.photo3.equals("default")) photoTemplate(user.photo3)
                                                            if (!user.photo4.equals("default")) photoTemplate(user.photo4)
                                                            if (!user.photo5.equals("default")) photoTemplate(user.photo5)
                                                            if (!user.photo6.equals("default")) photoTemplate(user.photo6)
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

            }

            scripTempate()
        }

    }

}
