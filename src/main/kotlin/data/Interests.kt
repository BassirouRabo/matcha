package data

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column

object Interests : IntIdTable() {
    val userId : Column<EntityID<Int>> = reference("interest_userId", Users.id)
    val favoris : Column<String> = varchar("favoris", 50)
}


class Interest(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Interest>(Interests)

    var userId by Interests.userId
    var favoris by Interests.favoris
}

data class InterestData(val userId: EntityID<Int>,
                        val favoris: String)