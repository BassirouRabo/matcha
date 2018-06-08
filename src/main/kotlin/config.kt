import data.*
import io.ktor.network.util.ioCoroutineDispatcher
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.withContext
import kotlinx.coroutines.experimental.yield
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.InputStream
import java.io.OutputStream

val SESSION: String = "SESSION"
val PHOTO: String = "default"
val PHOTOFULL: String = "default"
val PHOTO_SRC: String = "src/main/resources/photos"
val MSG_CHAT = "CHAT"
val MSG_ONLINE = "ONLINE"
val MSG_LIKE = "like"
val MSG_UNLIKE = "unlike"
val MSG_VISIT = "visit"
val MSG_SEPARATOR = "||"
val MSG_INIT = "INIT"

val SELECT = "SQL: SELECT users.id, users.username, users.email, users.first_name, users.last_name, users.age, users.\"password\", users.gender, users.campus, users.is_activate, users.is_report, users.is_online, users.code, users.\"date\", users.biography, users.score, users.orientation, users.tab_bio, users.tab_geek, users.tab_piercing, users.tab_smart, users.tab_shy, users.photo, users.photo1, users.photo2, users.photo3, users.photo4, users.photo5, users.photo6 FROM users WHERE users.id = 10"
val INSERT = "SQL: INSERT INTO users (age, biography, campus, code, \"date\", email, first_name, gender, is_activate, is_online, last_name, orientation, \"password\", photo, photo1, photo2, photo3, photo4, photo5, photo6, score, tab_bio, tab_geek, tab_piercing, tab_shy, tab_smart, username) VALUES (99, 'My biography', 1, 1234, '2018-06-05 16:59:48.925000', 'bachir@yahoo.fr', 'bachir RABO', 1, false, false, 'hima', 0, '202CB962AC59075B964B07152D234B70', 'default', 'default', 'default', 'default', 'default', 'default', 'default', 0, false, false, false, false, false, 'bachir')"


object db {
    fun connect() {
        Database.connect("jdbc:postgresql://localhost/matcha", driver = "org.postgresql.Driver", user = "brabo-hi", password = "brabo-hi")
    }

    fun init() {
        val a = SELECT
        val b = INSERT
        transaction {
            logger.addLogger(StdOutSqlLogger)
            create(Chats, Interests, Likes, Users, Visits, Bloques)
        }
    }
}

enum class Gender { MALE, FEMALE }

enum class Favori { A, B, C, D }

enum class Campus { PARIS, FREMONT }

enum class Matching { A0B, A1B, B1A, A2B }

enum class Orientation { BI, HO }


suspend fun InputStream.copyToSuspend(
        out: OutputStream,
        bufferSize: Int = DEFAULT_BUFFER_SIZE,
        yieldSize: Int = 4 * 1024 * 1024,
        dispatcher: CoroutineDispatcher = ioCoroutineDispatcher
): Long {
    return withContext(dispatcher) {
        val buffer = ByteArray(bufferSize)
        var bytesCopied = 0L
        var bytesAfterYield = 0L
        while (true) {
            val bytes = read(buffer).takeIf { it >= 0 } ?: break
            out.write(buffer, 0, bytes)
            if (bytesAfterYield >= yieldSize) {
                yield()
                bytesAfterYield %= yieldSize
            }
            bytesCopied += bytes
            bytesAfterYield += bytes
        }
        return@withContext bytesCopied
    }
}
