package api.istoki.entity

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "teachers")
class TeacherEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val teacherId: Int = 0,
    val teacherLogin: String?,
    var teacherPassword: String?,
    val teacherFirstName: String?,
    val teacherLastName: String?,
    val teacherEmail: String?,
    val teacherAvatar: String,
    val teacherPhone: Long = 0,
    @OneToMany(mappedBy = "languageTeacher")
    val teacherLanguages: List<LanguageEntity> = listOf(),
) {
    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.teacherPassword)
    }
}