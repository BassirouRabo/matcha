import data.User
import org.jetbrains.exposed.sql.transactions.transaction
import repository.LikeRepository
import repository.UserRepository

fun getGender(gender: Gender) = when (gender) {
    Gender.MALE -> "Male"
    Gender.FEMALE -> "Female"
}

fun getCampus(campus: Campus) = when (campus) {
    Campus.PARIS -> "Paris"
    Campus.FREMONT -> "Fremont"
}

fun getFriends(username: String) : List<User> {
    val names : MutableList<String> = mutableListOf()
    val friends : MutableList<User> = mutableListOf()

    transaction { LikeRepository.getLikes(username).forEach { like -> LikeRepository.getLikeds(username).forEach { if (it.username1 == like.username2) names.add(like.username2) } } }
    transaction { names.forEach { UserRepository.getByUsername(it)?.let { friends.add(it) } } }
    return friends
}