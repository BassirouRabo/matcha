package data

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Date
import org.joda.time.DateTime

object Likes : IntIdTable() {
    val user1Id = reference("like_user1_id", Users.id)
    val user2Id = reference("like_user2_id", Users.id)
    val date : Column<DateTime> = datetime("date")
}

class Like(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Like>(Likes)

    var user1Id by Likes.user1Id
    var user2Id by Likes.user2Id
    val date by Likes.date
}