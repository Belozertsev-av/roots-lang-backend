package api.istoki.response.exception

data class ApiError(
    val errorCode: Int,
    val errorText: String,
    val errorDesc: String,
)
