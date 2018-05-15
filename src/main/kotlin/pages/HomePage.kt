package pages


import io.ktor.application.ApplicationCall
import io.ktor.request.uri
import io.ktor.response.respondText
import Gender.*
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction
import repository.UserRepository
import data.User
import data.UserData


suspend fun ApplicationCall.homePage() {
   val cards : Array<String?> = arrayOf("Monday", null, "Wednesday")
    cards.forEach { it?.let { println(it) } }

    transaction {
        logger.addLogger(StdOutSqlLogger)
        val add: User? = UserRepository.add(UserData("username4", "email", "first name", "last name", 20, "password", "photo", M, "biographie", true, 255))


        UserRepository.getAll().forEach { println(it.toString()) }
    }
    respondText { request.uri }
}
