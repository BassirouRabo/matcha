package data

import data.Bloques.reference
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.joda.time.DateTime

object Bloques : IntIdTable() {
    val username1: Column<String> = reference("bloque_username1", Users.username)
    val username2: Column<String> = reference("bloque_username2", Users.username)
    val date: Column<DateTime> = datetime("date")
}

class Bloque(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Bloque>(Bloques)

    var username1 by Bloques.username1
    var username2 by Bloques.username2
    var date by Bloques.date
}

data class BloqueData(var username1: String,
                     val username2: String,
                     val date: DateTime)