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
                        src = if (user.photo.equals("default")) "public/photos/170x170.png" else "public/photos/${user.photo}"
                        alt = user.username
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