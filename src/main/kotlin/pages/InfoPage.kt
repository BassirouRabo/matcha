package pages

import data.Users
import io.ktor.application.ApplicationCall
import io.ktor.html.respondHtml
import io.ktor.locations.locations
import kotlinx.html.*
import template.headTemplate
import template.registerFootTemplate
import template.scripTempate
import InfoUrl
import data.User

suspend fun ApplicationCall.infoPage(user: User) {
    respondHtml {

        head { headTemplate("User Info") }

        body(classes = "sign-in") {
            div(classes = "wrapper") {
                div(classes = "sign-in-page") {
                    div(classes = "signin-popup") {
                        div(classes = "signin-pop") {
                            div(classes = "row") {
                                div(classes = "col-lg-6") {
                                    div(classes = "cmp-info") {
                                        div(classes = "cm-logo") { }
                                        img(src = "/public/images/logo_42_dating.png") {
                                            alt = ""
                                        }
                                    }
                                }
                                div(classes = "col-lg-6") {
                                    div(classes = "login-sec") {
                                        div(classes = "sign_in_sec current") {
                                            h3 { +"Interests" }
                                            form(locations.href(InfoUrl(user.username)), encType = FormEncType.multipartFormData, method = FormMethod.post) {
                                                div(classes = "row") {
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            select {
                                                                name = Users.orientation.name
                                                                required = true
                                                                option {
                                                                    value = Orientation.BI.toString()
                                                                    +"Bisexuel"
                                                                }
                                                                option {
                                                                    value = Orientation.HO.toString()
                                                                    +"Homosexuel"
                                                                }
                                                            }
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            checkBoxInput {
                                                                name = Users.tagBio.name
                                                                + "Bio"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            checkBoxInput {
                                                                name = Users.tagGeek.name
                                                                + "Geek"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            checkBoxInput {
                                                                name = Users.tagPiercing.name
                                                                + "Piercing"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            checkBoxInput {
                                                                name = Users.tagSmart.name
                                                                + "Smart"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        div(classes = "sn-field") {
                                                            checkBoxInput {
                                                                name = Users.tagShy.name
                                                                + "Shy"
                                                            }
                                                        }
                                                    }
                                                    div(classes = "col-lg-12 no-pdd") {
                                                        button() {
                                                            type = ButtonType.submit
                                                            value = "submit"
                                                            +"Update"
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
                    registerFootTemplate()
                }
            }
            scripTempate()
        }

    }
}