package repository

import PHOTO
import PHOTOFULL
import data.User
import data.UserData
import data.Users
import org.apache.commons.codec.digest.DigestUtils
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime


object UserRepository {

    fun getAll(): List<User> {
        return User.all().toList()
    }

    fun getAll(op: Op<Boolean>): List<User> {
        return User.find { op }.toList()
    }

    fun get(id: Int): User? {
        return User.findById(id)
    }

    fun get(op: Op<Boolean>): User? {
        val find = User.find { op }
        if (find.empty()) return null
        return find.single()
    }

    fun getByUsername(username: String): User? {
        val users: List<User> = getAll(Users.username eq username)
        return if (users.isEmpty() || users.size != 1) null else users.first()
    }

    fun getByUsernameAndPassword(username: String, password: String): User? {
        val users: List<User> = getAll(Users.username.eq(username) and Users.password.eq(DigestUtils.md5Hex(password).toUpperCase()))
        return if (users.isEmpty() || users.size != 1) null else users.first()
    }

    fun report(username: String) {
        val user = getByUsername(username)
        if (user != null) { user.isReport = true }
    }

    fun add(userData: UserData): User? {
        if (getByUsername(userData.username) != null) return null
        val id = Users.insertAndGetId {
            it[Users.username] = userData.username
            it[Users.email] = userData.email
            it[Users.firstName] = userData.firstName
            it[Users.lastName] = userData.lastName
            it[Users.age] = userData.age
            it[Users.password] = DigestUtils.md5Hex(userData.password).toUpperCase()
            it[Users.gender] = userData.gender
            it[Users.campus] = userData.campus
            it[Users.biography] = userData.biography
            it[Users.isActivate] = userData.isActivate
            it[Users.isOnline] = false
            it[Users.code] = userData.code
            it[Users.score] = 0
            it[Users.date] = DateTime.now()
            it[Users.orientation] = Orientation.BI
            it[Users.tagBio] = false
            it[Users.tagGeek] = false
            it[Users.tagPiercing] = false
            it[Users.tagSmart] = false
            it[Users.tagShy] = false
            it[Users.photo] = PHOTO
            it[Users.photo1] = PHOTOFULL
            it[Users.photo2] = PHOTOFULL
            it[Users.photo3] = PHOTOFULL
            it[Users.photo4] = PHOTOFULL
            it[Users.photo5] = PHOTOFULL
            it[Users.photo6] = PHOTOFULL
        }.value
        return get(id)
    }

    fun update(username: String, userData: UserData): User? {
        val user = getByUsername(username = username) ?: return null
        user.username = userData.username
        user.email = userData.email
        user.firstName = userData.firstName
        user.lastName = userData.lastName
        user.age = userData.age
        user.password = userData.password
        user.gender = userData.gender
        user.campus = userData.campus
        user.biography = userData.biography
        user.isActivate = userData.isActivate
        user.isOnline = userData.isOnline
        user.code = userData.code
        user.score = userData.score
        user.date = userData.date
        user.orientation = userData.orientation
        user.tagBio = userData.tagBio
        user.tagGeek = userData.tagGeek
        user.tagPiercing = userData.tagPiercing
        user.tagSmart = userData.tagSmart
        user.tagShy = userData.tagShy
        user.photo = userData.photo
        user.photo1 = userData.photo1
        user.photo2 = userData.photo2
        user.photo3 = userData.photo3
        user.photo4 = userData.photo4
        user.photo5 = userData.photo5
        user.photo6 = userData.photo6
        return user
    }

    fun delete(id: Int) {
        get(id)?.delete()
    }

    fun userToUserdate(user: User, userData: UserData): UserData {
        userData.username = user.username
        userData.email = user.email
        userData.firstName = user.firstName
        userData.lastName = user.lastName
        userData.age = user.age
        userData.password = user.password
        userData.gender = user.gender
        userData.biography = user.biography
        userData.isActivate = user.isActivate
        userData.isOnline = user.isOnline
        userData.score = user.score
        userData.date = user.date
        userData.orientation = user.orientation
        userData.tagBio = user.tagBio
        userData.tagGeek = user.tagGeek
        userData.tagPiercing = user.tagPiercing
        userData.tagSmart = user.tagSmart
        userData.tagShy = user.tagShy
        userData.photo = user.photo
        userData.photo1 = user.photo1
        userData.photo2 = user.photo2
        userData.photo3 = user.photo3
        userData.photo4 = user.photo4
        userData.photo5 = user.photo5
        userData.photo6 = user.photo6
        return userData
    }

    fun toUserdate(user: User): UserData {
        return UserData(username = user.username,
                email = user.email,
                firstName = user.firstName,
                lastName = user.lastName,
                age = user.age,
                password = user.password,
                gender = user.gender,
                campus = user.campus,
                biography = user.biography,
                isActivate = user.isActivate,
                isOnline = user.isOnline,
                code = user.code,
                score = user.score,
                date = user.date,
                orientation = user.orientation,
                tagBio = user.tagBio,
                tagGeek = user.tagGeek,
                tagPiercing = user.tagPiercing,
                tagSmart = user.tagSmart,
                tagShy = user.tagShy,
                photo = user.photo,
                photo1 = user.photo1,
                photo2 = user.photo2,
                photo3 = user.photo3,
                photo4 = user.photo4,
                photo5 = user.photo5,
                photo6 = user.photo6
        )
    }

}

