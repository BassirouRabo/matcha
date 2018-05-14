import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun db() {
    Database.connect("jdbc:mysql://localhost/matcha", driver = "com.mysql.jdbc.Driver", user = "root", password = "root")

    transaction {
        logger.addLogger(StdOutSqlLogger)
    }
}