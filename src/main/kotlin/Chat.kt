import data.User
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.experimental.channels.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import repository.ChatRepository
import repository.UserRepository
import java.util.*
import java.util.concurrent.*
import java.util.concurrent.atomic.*
import kotlin.collections.HashMap

object Chat {
    val members : MutableMap<String, WebSocketSession> = mutableMapOf()

    suspend fun memberJoin(user : User, socket: WebSocketSession) {
        if (!members.containsKey(user.username)) {
            transaction {
                UserRepository.getByUsername(user.username)?.let {
                    it.isOnline = true
                    it.date = DateTime.now()
                }
            }
            members[user.username] = socket
            broadcast(user.username)
        }
    }

    fun memberLeft(user: User) {
        members.remove(user.username)
        transaction {
            UserRepository.getByUsername(user.username)?.let {
                it.isOnline = false
                it.date = DateTime.now()
            }
        }
    }

    suspend fun sendMessage(user1 : User, user2: User, message : String) {
        members[user2.username]?.let { it.send(Frame.Text("$MSG_CHAT$MSG_SEPARATOR${user2.username}$MSG_SEPARATOR$message")) }
        transaction {  ChatRepository.add(username1 = user1.username, username2 = user2.username, message = message) }
    }

    suspend fun broadcast(username : String) {
        for ((key, value) in members) {
            if (key != username) { value.send(frame = Frame.Text("$MSG_ONLINE$MSG_SEPARATOR$username")) }
        }
    }

}