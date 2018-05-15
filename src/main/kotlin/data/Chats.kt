package data

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.Column
import org.joda.time.DateTime

object Chats : IntIdTable() {
    val user1Id : Column<EntityID<Int>> = Likes.reference("chart_user1_id", Users.id)
    val user2Id : Column<EntityID<Int>> = Likes.reference("chart_user2_id", Users.id)
    val message : Column<String> = text("message")
    val date : Column<DateTime> = datetime("date")
}

class Chat(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Chat>(Chats)

    var user1Id by Chats.user1Id
    var user2Id by Chats.user2Id
    val message by Chats.message
    val date by Chats.date
}
