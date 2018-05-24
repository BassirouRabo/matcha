package template

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion
import io.ktor.locations.locations
import kotlinx.html.*
import UserUrl
import data.User

fun DIV.userTemplate(user: User) : Unit {
    div(classes = "post-bar") {
        div(classes = "post_topbar") {
            a {
                href = "/${user.username}"
                title = "Profil : ${user.username}"
                div(classes = "usy-dt") {
                    img {
                        src = "http://via.placeholder.com/50x50"
                        alt = ""
                    }
                    div(classes = "usy-name") {
                        h3 { +" ${user.username}" }
                        span {
                            img {
                                src = "images/clock.png"
                                alt = ""
                            }
                            +" ${user.firstName}"
                        }
                    }
                }
            }
        }
    }
}