package pages

import io.ktor.application.ApplicationCall
import io.ktor.locations.Location
import io.ktor.request.uri
import io.ktor.response.respondText

import UserUrl

suspend fun ApplicationCall.userPage(userUrl: UserUrl) {
    respondText { request.uri + " " + userUrl.username }
}
