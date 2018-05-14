package pages

import io.ktor.application.ApplicationCall
import io.ktor.request.uri
import io.ktor.response.respondText

suspend fun ApplicationCall.registerPage() {
    respondText { request.uri }
}