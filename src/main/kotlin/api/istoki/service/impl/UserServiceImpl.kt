package api.istoki.service.impl

import api.istoki.dto.LoginDto
import api.istoki.dto.ScoresDto
import api.istoki.dto.UserDto
import api.istoki.entity.ScoresEntity
import api.istoki.entity.UserEntity
import api.istoki.repository.FeedbackRepository
import api.istoki.repository.LanguageRepository
import api.istoki.repository.ScoresRepository
import api.istoki.repository.UserRepository
import api.istoki.response.exception.*
import api.istoki.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.servlet.http.HttpServletResponse


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val languageRepository: LanguageRepository,
    private val scoresRepository: ScoresRepository,
    private val feedbackRepository: FeedbackRepository
) :
    UserService {
    override fun getAll(page: Int): List<UserDto> {
        return userRepository.findByOrderByUserId(PageRequest.of(page, 25)).map { it.toDto() }
    }

    override fun findById(userId: Int): UserDto {
        return userRepository.findByIdOrNull(userId)?.toDto()
            ?: throw NotFoundException("User", userId)
    }

    override fun findByParam(login: String?, mail: String?, tel: Long?, prefix: String?): Any? {
        return if (login != null) {
            if (userRepository.findByUserLoginIgnoreCaseOrderByUserLogin(login) != null)
                userRepository.findByUserLoginIgnoreCaseOrderByUserLogin(login)!!.toDto().login
            else
                "error"
        } else if (tel != null) {
            if (userRepository.findByUserPhoneOrderByUserPhone(tel) != null)
                userRepository.findByUserPhoneOrderByUserPhone(tel)!!.toDto().phoneNumber
            else
                "error"
        } else if (mail != null) {
            if (userRepository.findByUserMailIgnoreCaseOrderByUserMail(mail) != null)
                userRepository.findByUserMailIgnoreCaseOrderByUserMail(mail)!!.toDto().mail
            else
                "error"
        } else if (prefix != null) {
            userRepository.findByUserFirstNameIgnoreCaseStartsWithOrderByUserFirstName(prefix)
                .map { it.toDto() }
        } else "error"
    }

    override fun createUser(dto: UserDto): Int {
        return userRepository.save(dto.toEntity()).userId
    }

    override fun editUser(userId: Int, pass: Boolean?, dto: UserDto): String {
        val currUser = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException("User", userId)
        if (pass != null) {
            return if (currUser.comparePassword(dto.password)) {
                "Пароль не изменен, вы ввели старый пароль"
            } else {
                currUser.userPassword = BCryptPasswordEncoder().encode(dto.password) ?: currUser.userPassword
                userRepository.save(currUser)
                "Пароль успешно изменен"
            }
        } else {
            currUser.userFirstName = dto.fName ?: currUser.userFirstName
            currUser.userLastName = dto.lName ?: currUser.userLastName
            currUser.userPhone = dto.phoneNumber ?: currUser.userPhone
            currUser.userLogin = dto.login
            currUser.userPassword = dto.password ?: currUser.userPassword
            currUser.userAvatar = dto.avatar ?: currUser.userAvatar
            currUser.userMail = dto.mail ?: currUser.userMail

            userRepository.save(currUser)
            return "Данные успешно обновлены"
        }
    }

    @Transactional
    override fun deleteUser(userId: Int) {
        val currUser = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException("User", userId)
        userRepository.deleteById(currUser.userId)
    }

    @Transactional
    override fun addLanguage(langId: Int, userId: Int): String {
        val currUser = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException("User", userId)

        val targetLang = languageRepository.findByIdOrNull(langId)
            ?: throw NotFoundException("User", userId)

        fun isContains(): Boolean {
            for (i in currUser.userLanguages) {
                return i.scoresLanguage.languageId == targetLang.languageId
            }
            return false
        }
        if (!isContains()) {
            val newLang = ScoresEntity(
                scoresId = 0,
                scoresUser = currUser,
                scoresLanguage = targetLang,
                scores = 0
            )

            val scoresIndex: Int = scoresRepository.save(newLang).scoresId
            currUser.userLanguages += scoresRepository.getByScoresId(scoresIndex)
            userRepository.save(currUser)
        } else {
            throw AlreadyExistsException("Language", userId)
        }
        return "Language successfully added to user with id $userId"
    }

    @Transactional
    override fun deleteLanguage(langId: Int, userId: Int): String {
        val currUser = userRepository.findByIdOrNull(userId)
            ?: throw NotFoundException("User", userId)

        val targetLang = languageRepository.findByIdOrNull(langId)
            ?: throw NotFoundException("User", userId)

        var scoresIndex = 0

        for (i in currUser.userLanguages) {
            if (i.scoresLanguage.languageId == targetLang.languageId) scoresIndex = i.scoresId
        }

        if (scoresIndex != 0) {
            scoresRepository.deleteById(scoresIndex)
        } else {
            throw AlreadyExistsException("Language", userId)
        }
        return "Language with id $langId successfully deleted from user with id $userId"
    }

    override fun login(dto: LoginDto, response: HttpServletResponse): String {
        val user = userRepository.findByUserLoginIgnoreCase(dto.login)
            ?: throw LoginNotFoundException(dto)
        if (!user.comparePassword(dto.password)) {
            throw PasswordNotFoundException()
        }

        return Jwts.builder()
            .setIssuer(user.userId.toString())
            .setExpiration(Date(System.currentTimeMillis() + 30 * 24 * 60 * 60)) // 1 week
            .signWith(SignatureAlgorithm.HS512, "N3QQIr3V").compact()
    }

    override fun getUserData(jwt: String?): UserDto {
        try {
            if (jwt == null) throw UnauthenticatedException()


            val body = Jwts.parser().setSigningKey("N3QQIr3V").parseClaimsJws(jwt).body

            return userRepository.getByUserId(body.issuer.toInt()).toDto()
        } catch (e: Exception) {
            throw UnauthenticatedException()
        }
    }

    override fun logout(jwt: String, response: HttpServletResponse): String {
// Delete the method
        return "You've just logged out"
    }

    private fun UserEntity.toDto(): UserDto =
        UserDto(
            Id = this.userId,
            login = this.userLogin,
            password = this.userPassword,
            fName = this.userFirstName,
            lName = this.userLastName,
            mail = this.userMail,
            avatar = this.userAvatar,
            phoneNumber = this.userPhone,
            userLanguages = this.userLanguages.map { it.toDto() }
        )

    private fun ScoresEntity.toDto(): ScoresDto =
        ScoresDto(
            scoresId = this.scoresId,
            scoresUser = this.scoresUser.userId,
            scoresLanguage = this.scoresLanguage.languageId,
            scores = this.scores
        )

    private fun UserDto.toEntity(): UserEntity {
        return UserEntity(
            userId = 0,
            userLogin = this.login,
            userPassword = BCryptPasswordEncoder().encode(this.password),
            userFirstName = this.fName,
            userLastName = this.lName,
            userMail = this.mail,
            userAvatar = this.avatar,
            userPhone = this.phoneNumber,
            userFeedback = feedbackRepository.getByUserId(this.Id)
        )
    }
}