package repository

import data.Chat
import data.Chats
import data.User
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

object ChatRepository {
    fun getAll(): List<Chat> {
        return Chat.all().toList()
    }

    fun getAll(op: Op<Boolean>): List<Chat> {
        return Chat.find { op }.toList()
    }

    fun getChats(username: String): List<User> {
        val list : MutableList<User> = mutableListOf()

        getAll(Chats.username1.eq(username)).forEach {
            transaction {
                val user = UserRepository.getByUsername(it.username1)
                if (user != null && username  != user.username && !list.contains(user)) list.add(user)
            }
        }
        getAll(Chats.username2.eq(username)).forEach {
            transaction {
                val user = UserRepository.getByUsername(it.username1)
                if (user != null && username  != user.username && !list.contains(user)) list.add(user)
            }
        }
       return  list
    }

    fun get(op: Op<Boolean>): Chat? {
        val find = Chat.find { op }
        if (find.empty()) return null
        return find.single()
    }

    fun get(id: Int): Chat? {
        return Chat.findById(id)
    }

    fun getAll(username1: String, username2: String): List<Chat> {
        return getAll(Chats.username1.eq(username1) and Chats.username2.eq(username2))
    }

    fun add(username1: String, username2: String, message : String): Chat? {
        val id = Chats.insertAndGetId {
            it[Chats.username1] = username1
            it[Chats.username2] = username2
            it[Chats.message] = message
            it[Chats.date] = DateTime.now()
        }.value
        return (get(id))
    }




}