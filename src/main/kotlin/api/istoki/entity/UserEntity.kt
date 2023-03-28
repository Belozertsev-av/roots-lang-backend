package api.istoki.entity

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Int = 0,
    var userLogin: String?,
    var userPassword: String?,
    var userFirstName: String?,
    var userLastName: String?,
    var userMail: String?,
    var userAvatar: String?,
    var userPhone: Long? = 0,
    @OneToOne(mappedBy = "feedbackUser")
    val userFeedback: FeedbackEntity?,
    @OneToMany(mappedBy = "scoresUser")
    var userLanguages: MutableList<ScoresEntity> = mutableListOf(),
) {
    fun comparePassword(password: String?): Boolean {
        return BCryptPasswordEncoder().matches(password, this.userPassword)
    }
}