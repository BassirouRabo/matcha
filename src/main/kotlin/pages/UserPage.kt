package pages

import io.ktor.application.ApplicationCall
import io.ktor.locations.Location
import io.ktor.request.uri
import io.ktor.response.respondText

import UserUrl
import data.User

suspend fun ApplicationCall.userPage(user: User) {
    respondText { request.uri + " " + user.username }
}
