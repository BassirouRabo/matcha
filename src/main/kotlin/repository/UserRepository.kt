package repository

import org.jetbrains.exposed.sql.insertAndGetId
import data.Users
import data.User
import data.UserData


object UserRepository {

    fun getAll() : List<User> {
       return User.all().toList()
    }

    fun get(id: Int) : User? {
        return User.findById(id)
    }

    fun add(userData: UserData): User? {
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

