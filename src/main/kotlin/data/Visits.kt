package data

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.joda.time.DateTime

object Visits : IntIdTable() {
    val username1 : Column<String> = reference("visit_username1", Users.username)
    val username2 : Column<String> = reference("visit_username2", Users.username)
    val date : Column<DateTime> = datetime("date")
}

class Visit(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Visit>(Visits)

    var username1 by Visits.username1
    var username2 by Visits.username2
    var date by Visits.date
}

data class VisitData(var username1: String,
                     val username2: String,
                     val date: DateTime)