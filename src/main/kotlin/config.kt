import data.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

val SESSION : String = "SESSION"

object db {
    fun connect() {
        Database.connect("jdbc:postgresql://localhost/matcha", driver = "org.postgresql.Driver", user = "brabo-hi", password = "brabo-hi")
    }

    fun init() {
        transaction {
            logger.addLogger(StdOutSqlLogger)
            create(Chats, Interests, Likes, Users, Visits)
        }
    }
}

enum class Gender { M, F }

enum class Favori { A, B, C, D}