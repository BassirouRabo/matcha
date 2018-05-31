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

object db {
    fun connect() {
        Database.connect("jdbc:postgresql://localhost/matcha", driver = "org.postgresql.Driver", user = "brabo-hi", password = "brabo-hi")
    }

    fun init() {
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