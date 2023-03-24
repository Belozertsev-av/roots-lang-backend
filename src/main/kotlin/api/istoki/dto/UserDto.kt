package api.istoki.dto

data class UserDto(
    val Id: Int? = null,
    val login: String?,
    val password: String?,
    val fName: String?,
    val lName: String?,
    val avatar: String?,
    val mail: String?,
    val phoneNumber: Long?,
    val userLanguages: List<ScoresDto>,
)
