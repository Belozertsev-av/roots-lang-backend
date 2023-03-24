package api.istoki.service.impl

import api.istoki.dto.LoginDto
import api.istoki.dto.TeacherDto
import api.istoki.entity.TeacherEntity
import api.istoki.repository.TeacherRepository
import api.istoki.response.exception.LoginNotFoundException
import api.istoki.response.exception.NotFoundException
import api.istoki.response.exception.PasswordNotFoundException
import api.istoki.response.exception.UnauthenticatedException
import api.istoki.service.TeacherService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
class TeacherServiceImpl(private val teacherRepository: TeacherRepository) : TeacherService {
    override fun getAll(): List<TeacherDto> {
        return teacherRepository.findByOrderByTeacherId().map { it.toDto() }
    }

    override fun getTeacherById(id: Int): TeacherDto {
        val teacher = teacherRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Teacher", id)
        return teacher.toDto()
    }

    override fun createTeacher(dto: TeacherDto): String {

        teacherRepository.save(dto.toEntity())
        return "Teacher successfully added"

    }

    override fun login(dto: LoginDto, response: HttpServletResponse): String {
        val teacher = teacherRepository.findByTeacherLogin(dto.login)
            ?: throw LoginNotFoundException(dto)
        if (!teacher.comparePassword(dto.password)) {
            throw PasswordNotFoundException()
        }
        val jwt = Jwts.builder()
            .setIssuer(teacher.teacherId.toString())
            .setExpiration(Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
            .signWith(SignatureAlgorithm.HS512, "N3QQIr3V").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)

        return "You successfully logged in"
    }

    override fun getTeacherData(jwt: String?): TeacherDto {
        try {
            if (jwt == null) throw UnauthenticatedException()


            val body = Jwts.parser().setSigningKey("N3QQIr3V").parseClaimsJws(jwt).body

            return teacherRepository.getByTeacherId(body.issuer.toInt()).toDto()
        } catch (e: Exception) {
            throw UnauthenticatedException()
        }
    }

    override fun logout(response: HttpServletResponse): String {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)
        return "You've just logged out"
    }

    private fun TeacherEntity.toDto(): TeacherDto =
        TeacherDto(
            id = this.teacherId,
            login = this.teacherLogin,
            password = this.teacherPassword,
            fName = this.teacherFirstName,
            lName = this.teacherLastName,
            email = this.teacherEmail,
            avatar = this.teacherAvatar,
            phoneNumber = this.teacherPhone,
            languages = this.teacherLanguages
        )

    private fun TeacherDto.toEntity(): TeacherEntity {
        return TeacherEntity(
            teacherId = 0,
            teacherLogin = this.login,
            teacherPassword = BCryptPasswordEncoder().encode(this.password),
            teacherFirstName = this.fName,
            teacherLastName = this.lName,
            teacherEmail = this.email,
            teacherAvatar = this.avatar,
            teacherPhone = this.phoneNumber,
            teacherLanguages = this.languages
        )
    }


}