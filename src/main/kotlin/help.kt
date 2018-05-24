fun getGender(gender: Gender) = when (gender) {
    Gender.MALE -> "Male"
    Gender.FEMALE -> "Female"
    else -> "Male"
}

fun getCampus(campus: Campus) = when (campus) {
    Campus.PARIS -> "Paris"
    Campus.FREMONT -> "Fremont"
    else -> "Paris"
}