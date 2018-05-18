package repository

import data.Like
import data.Likes
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.joda.time.DateTime

object LikeRepository {
    fun getAll() : List<Like> {
        return Like.all().toList()
    }

    fun getAll(op: Op<Boolean>) : List<Like> {
        return Like.find { op }.toList()
    }

    fun getAll(username: String) : List<Like> {
        return getAll(Likes.username1.eq(username))
    }

    fun get(op: Op<Boolean>): Like? {
        val find = Like.find { op }
        if (find.empty()) return null
        return find.single()
    }

    fun get(id: Int) : Like? {
        return Like.findById(id)
    }

    fun get(username1: String, username2: String) : Like? {
        return get(Likes.username1.eq(username1) and Likes.username2.eq(username2))
    }

    fun add(username1: String, username2: String): Like? {
        if (get(username1, username2) != null) return null
        val id = Likes.insertAndGetId {
            it[Likes.username1] = username1
            it[Likes.username2] = username2
            it[Likes.date] = DateTime.now()
        }.value
        return (get(id))
    }

    fun delete(username1: String, username2: String) {
        get(username1, username2)?.delete()
    }
}