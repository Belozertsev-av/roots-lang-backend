package api.istoki.response.exception

import org.springframework.http.HttpStatus

class AlreadyExistsException(title: String, id: Int?) : BaseException(
    HttpStatus.FORBIDDEN,
    ApiError(
        errorCode = 403,
        errorText = "$title already exists",
        errorDesc = if (id == null) "$title is already exists." else "$title with $id is already exists."
    )
)