import data.User
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.*
import org.jetbrains.exposed.sql.transactions.transaction
import repository.LikeRepository
import template.*

suspend fun ApplicationCall.userPage(user: User) {

    val username = sessions.get<Session>()!!.username

    respondHtml {
        head { headTemplate(user.username) }

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
                                                    img() {
                                                        src = "http://via.placeholder.com/170x170"
                                                        alt = ""
                                                    }
                                                }
                                                div(classes = "user_pro_status") {
                                                    ul(classes = "flw-hr") {
                                                        li {
                                                            transaction {
                                                                val matching = LikeRepository.getMatch(username, user.username)
                                                                println("matching ${matching}")
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
                                                        li {
                                                            a(classes = "logout") {
                                                                href = locations.href("#")
                                                                title = "Block"
                                                                i(classes = "la la-user-times") {}
                                                                +"Block"
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
