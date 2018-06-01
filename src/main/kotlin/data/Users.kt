package data

import Campus
import Gender
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import Orientation
import org.joda.time.DateTime

object Users : IntIdTable() {
    val username: Column<String> = varchar("username", 20).uniqueIndex()
    val email: Column<String> = varchar("email", 50)
    val firstName: Column<String> = varchar("first_name", 50)
    val lastName: Column<String> = varchar("last_name", 50)
    val age: Column<Int> = integer("age")
    val password: Column<String> = varchar("password", 255)
    val gender = enumeration("gender", Gender::class.java)
    val campus = enumeration("campus", Campus::class.java)
    val isActivate: Column<Boolean> = bool("is_activate")
    val isReport: Column<Boolean> = bool("is_report")
    val isOnline: Column<Boolean> = bool("is_online")
    val code: Column<Int> = integer("code")
    val date: Column<DateTime> = datetime("date")

    val biography: Column<String> = text("biography")
    val score : Column<Int> = integer("score")
    val orientation = enumeration("orientation", Orientation::class.java)

    val tagBio : Column<Boolean> = bool("tab_bio")
    val tagGeek : Column<Boolean> = bool("tab_geek")
    val tagPiercing : Column<Boolean> = bool("tab_piercing")
    val tagSmart : Column<Boolean> = bool("tab_smart")
    val tagShy : Column<Boolean> = bool("tab_shy")

    val photo: Column<String> = varchar("photo", 255)
    val photo1: Column<String> = varchar("photo1", 255)
    val photo2: Column<String> = varchar("photo2", 255)
    val photo3: Column<String> = varchar("photo3", 255)
    val photo4: Column<String> = varchar("photo4", 255)
    val photo5: Column<String> = varchar("photo5", 255)
    val photo6: Column<String> = varchar("photo6", 255)
}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var username by Users.username
    var email by Users.email
    var firstName by Users.firstName
    var lastName by Users.lastName
    var age by Users.age
    var password by Users.password
    var gender by Users.gender
    var campus by Users.campus
    var isActivate by Users.isActivate
    var isReport by Users.isReport
    var isOnline by Users.isOnline
    var code by Users.code
    var date by Users.date
    var biography by Users.biography
    var score by Users.score
    var orientation by Users.orientation
    var tagBio by Users.tagBio
    var tagGeek by Users.tagGeek
    var tagPiercing by Users.tagPiercing
    var tagSmart by Users.tagSmart
    var tagShy by Users.tagShy
    var photo by Users.photo
    var photo1 by Users.photo1
    var photo2 by Users.photo2
    var photo3 by Users.photo3
    var photo4 by Users.photo4
    var photo5 by Users.photo5
    var photo6 by Users.photo6
}

data class UserData(var username: String,
                    var email: String,
                    var firstName: String,
                    var lastName: String,
                    var age: Int,
                    var password: String,
                    var gender: Gender,
                    var campus: Campus,
                    var isActivate: Boolean = false,
                    var isReport: Boolean = false,
                    var isOnline: Boolean = false,
                    var code: Int,
                    var date: DateTime,
                    var biography: String = "",
                    var score: Int = 0,
                    var orientation : Orientation = Orientation.BI,
                    var tagBio : Boolean = false,
                    var tagGeek : Boolean = false,
                    var tagPiercing : Boolean = false,
                    var tagSmart : Boolean = false,
                    var tagShy : Boolean = false,
                    var photo: String = "default",
                    var photo1: String = "default",
                    var photo2: String = "default",
                    var photo3: String = "default",
                    var photo4: String = "default",
                    var photo5: String = "default",
                    var photo6: String = "default")