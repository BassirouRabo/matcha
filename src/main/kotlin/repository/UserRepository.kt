package repository

import data.User

object UserRepository {

    fun getAll() : List<User> {
        return User.all().toList()
    }

    fun get(id: Int) : User? {
        return User.findById(id)
    }

    fun add(user: User?): User? {
        user ?: return null
        return  User.new {
            username = user.username
            email = user.email
            firstName = user.firstName
            lastName = user.lastName
            birthday = user.birthday
            password = user.password
            photo = user.photo
            gender = user.gender
            biography = user.biography
            isActivate = user.isActivate
            code = user.code
        }
    }

    fun delete(id: Int) {
        get(id)?.delete()
    }

    /*fun update(id: Int, user: User?) {
        var userFind = get(id)
        userFind?.biography = user?.biography
    }*/

}

