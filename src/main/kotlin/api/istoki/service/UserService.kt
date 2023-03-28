package api.istoki.service

import api.istoki.dto.LoginDto
import api.istoki.dto.UserDto
import javax.servlet.http.HttpServletResponse

interface UserService {
    fun getAll(page: Int): List<UserDto>

    fun findById(userId: Int): UserDto

    fun findByParam(login: String?, mail: String?, tel: Long?, prefix: String?): Any?

    fun createUser(dto: UserDto): Int

    fun editUser(userId: Int, pass: Boolean?, dto: UserDto): String

    fun deleteUser(userId: Int)

    fun addLanguage(langId: Int, userId: Int): String

    fun deleteLanguage(langId: Int, userId: Int): String

    fun login(dto: LoginDto, response: HttpServletResponse): String

    fun getUserData(jwt: String?): UserDto

    fun logout(jwt: String, response: HttpServletResponse): String
}