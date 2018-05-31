package template

import kotlinx.html.BODY
import kotlinx.html.script

fun BODY.scripTempate(): Unit {
    script(type = "text/javascript") { src = "/public/js/popper.js" }
    script(type = "text/javascript") { src = "/public/js/bootstrap.min.js" }
    script(type = "text/javascript") { src = "/public/js/jquery.mCustomScrollbar.js" }
    script(type = "text/javascript") { src = "/public/js/jquery.range-min.js" }
    script(type = "text/javascript") { src = "/public/js/flatpickr.min.js" }
    script(type = "text/javascript") { src = "/public/lib/slick/slick.min.js" }
    script(type = "text/javascript") { src = "/public/js/scrollbar.js" }
    script(type = "text/javascript") { src = "/public/js/script.js" }
}