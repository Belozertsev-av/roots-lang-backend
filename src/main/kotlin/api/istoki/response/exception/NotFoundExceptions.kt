package api.istoki.response.exception

import api.istoki.dto.LoginDto
import org.springframework.http.HttpStatus

class NotFoundException(name: String, id: Int) : BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = 404,
        errorText = "$name not found",
        errorDesc = "$name with id $id is not exists"
    )
)

class LoginNotFoundException(dto: LoginDto) : BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = 404,
        errorText = "Login not found",
        errorDesc = "Person with login ${dto.login} is not exists"
    )
)

class PasswordNotFoundException() : BaseException(
    HttpStatus.UNAUTHORIZED,
    ApiError(
        errorCode = 401,
        errorText = "Password not found",
        errorDesc = "Person with this password is not exists"
    )
)

class UnauthenticatedException() : BaseException(
    HttpStatus.UNAUTHORIZED,
    ApiError(
        errorCode = 401,
        errorText = "Unauthenticated",
        errorDesc = "You are not authenticated, you should login or sign up"
    )
)