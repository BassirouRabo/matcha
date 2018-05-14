package pages

import io.ktor.application.ApplicationCall
import io.ktor.request.uri
import io.ktor.response.respondText
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create

object Cities: IntIdTable() {
    val name = varchar("name", 50)
}

suspend fun ApplicationCall.homePage() {

    //Database.connect("jdbc:postgresql://localhost/matcha", driver = "org.postgresql.Driver", user = "brabo-hi", password = "brabo-hi")
    Database.connect("jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

    transaction {
        logger.addLogger(StdOutSqlLogger)

        create(Cities)

        val stPeteId = Cities.insert {
            it[name] = "St. Petersburg"
        } get Cities.id
        //println("stPeteId " +stPeteId)
        Cities.selectAll().forEach { println(it[Cities.name]) }
    }


    respondText { request.uri }
}
