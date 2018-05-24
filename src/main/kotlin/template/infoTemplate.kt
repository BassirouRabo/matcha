package template

import data.User
import getCampus
import getGender
import kotlinx.html.DIV
import kotlinx.html.i
import kotlinx.html.li
import kotlinx.html.ul

fun DIV.infoTemplete(user: User) : Unit {
    ul(classes = "social_links") {
        li {
            i(classes = "fa fa-instagram") {}
            +" ${user.username}"
        }
        li {
            i(classes = "fa fa-instagram") {}
            +" ${user.firstName}"
        }
        li {
            i(classes = "fa fa-instagram") {}
            +" ${user.lastName}"
        }
        li {
            i(classes = "fa fa-instagram") {}
            +" ${getGender(user.gender)}"
        }
        li {
            i(classes = "fa fa-instagram") {}
            +" ${getCampus(user.campus)}"
        }
    }
}