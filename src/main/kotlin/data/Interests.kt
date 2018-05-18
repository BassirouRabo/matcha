package data

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import Favori

object Interests : IntIdTable() {
    val username : Column<String> = reference("interest_username", Users.username)
    val favoris = enumeration("favoris", Favori::class.java)
}


class Interest(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Interest>(Interests)

    var username by Interests.username
    var favoris by Interests.favoris
}

data class InterestData(var username: String,
                        var favoris: Favori)