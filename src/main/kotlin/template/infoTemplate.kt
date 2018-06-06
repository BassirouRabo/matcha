package template

import data.User
import getCampus
import getGender
import kotlinx.html.DIV
import kotlinx.html.i
import kotlinx.html.li
import kotlinx.html.ul

fun DIV.infoTemplete(user: User)  {
    ul(classes = "social_links") {
        li {
            i(classes = "la la-tag") {}
            +"  ${user.username}"
        }
        li {
            i(classes = "la la-tag") {}
            +"  ${user.firstName}"
        }
        li {
            i(classes = "la la-tag") {}
            +"  ${user.lastName}"
        }
        li {
            i(classes = "la la-tag") {}
            +"  ${getGender(user.gender)}"
        }
        li {
            i(classes = "la la-tag") {}
            +"  ${user.orientation}"
        }
        li {
            i(classes = "la la-flag") {}
            +"  ${getCampus(user.campus)}"
        }
    }
}