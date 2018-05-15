package pages

import io.ktor.application.ApplicationCall
import io.ktor.request.uri
import io.ktor.response.respondText


suspend fun ApplicationCall.homePage() {
   val cards : Array<String?> = arrayOf("Monday", null, "Wednesday")
    cards.forEach { it?.let { println(it) } }
    respondText { request.uri }
}
