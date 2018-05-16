package repository

import data.Users
import data.User
import data.UserData
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


object UserRepository {

    fun getAll() : List<User> {
       return User.all().toList()
    }

    fun getAll(op: Op<Boolean>) : List<User> {
        return User.find { op }.toList()
    }

    fun get(id: Int) : User? {
        return User.findById(id)
    }

    fun get(op: Op<Boolean>) : User? {
        return User.find { op }.single()
    }

    fun getByUsername(username: String) : User? {
        val users = getAll(Users.username eq username)
        return if (users.isEmpty() || users.size != 1) null else users.first()
    }

    fun getByUsernameAndPassword(username: String, password: String) : User? {
        val users = getAll(Users.username.eq(username) and Users.password.eq(password))
        return if (users.isEmpty() || users.size != 1) null else users.first()
    }

    fun add(userData: UserData): User? {
        getByUsername(userData.username) == null ?: return null
        val id = Users.insertAndGetId {
            it[Users.username] = userData.username
            it[Users.email] = userData.email
            it[Users.firstName] = userData.firstName
            it[Users.lastName] = userData.lastName
            it[Users.age] = userData.age
            it[Users.password] = userData.password
            it[Users.photo] = userData.photo
            it[Users.gender] = userData.gender
            it[Users.biography] = userData.biography
            it[Users.isActivate] = userData.isActivate
            it[Users.code] = userData.code
        }
        return get(id.value)
    }

    fun update(id: Int, userData: UserData) : User? {
        val user = get(id)
        user ?: return null
        user.username = userData.username
        user.email = userData.email
        user.firstName = userData.firstName
        user.lastName = userData.lastName
        user.age = userData.age
        user.password = userData.password
        user.photo = userData.photo
        user.gender = userData.gender
        user.biography = userData.biography
        user.isActivate = userData.isActivate
        user.code = userData.code
        return user
    }

    fun delete(id: Int) {
        get(id)?.delete()
    }

}

