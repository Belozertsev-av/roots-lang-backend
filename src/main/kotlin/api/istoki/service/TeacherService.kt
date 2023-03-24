package api.istoki.service

import api.istoki.dto.LoginDto
import api.istoki.dto.TeacherDto
import javax.servlet.http.HttpServletResponse

interface TeacherService {
    fun getAll(): List<TeacherDto>

    fun getTeacherById(id: Int): TeacherDto

    fun createTeacher(dto: TeacherDto): String

    fun login(dto: LoginDto, response: HttpServletResponse): String

    fun getTeacherData(jwt: String?): TeacherDto

    fun logout(response: HttpServletResponse): String
}