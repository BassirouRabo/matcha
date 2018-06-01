package template

import data.User
import io.ktor.locations.locations
import kotlinx.html.*

fun DIV.sideOnlineTemplate( onlines: List<User>) {
    div(classes = "col-lg-3") {
        div(classes = "suggestionsth full-width") {
            div(classes = "sd-title") {
                h3 { + "Online" }
            }
            div(classes = "suggestions-list") { id = "online"
                onlines.forEach {
                    div(classes = "suggestion-usd") {
                        img {
                            src = "http://via.placeholder.com/35x35"
                        }
                        div(classes = "sgt-text") {
                            h4 { + it.username }
                        }
                        span {
                            a {
                                href = "/${it.username}"
                                i(classes = "la la-plus") {}
                            }
                        }

                    }
                }
            }
        }
    }
}