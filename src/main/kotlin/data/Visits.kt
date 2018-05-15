package data

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.joda.time.DateTime

object Visits : IntIdTable() {
    val user1Id = Likes.reference("visit_user1_id", Users)
    val user2Id = Likes.reference("visit_user2_id", Users)
    val date : Column<DateTime> = datetime("date")
}

class Visit(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Visit>(Visits)

    var user1Id by Visits.user1Id
    var user2Id by Visits.user2Id
    val date by Visits.date
}