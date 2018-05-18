package data

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.joda.time.DateTime

object Likes : IntIdTable() {
    val username1 : Column<String> = reference("like_username1", Users.username)
    val username2 : Column<String> = reference("like_username2", Users.username)
    val date : Column<DateTime> = datetime("date")
}

class Like(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Like>(Likes)

    var username1 by Likes.username1
    var username2 by Likes.username2
    var date by Likes.date
}

data class LikeData(var username1: String,
                    var username2: String,
                    val date: DateTime)