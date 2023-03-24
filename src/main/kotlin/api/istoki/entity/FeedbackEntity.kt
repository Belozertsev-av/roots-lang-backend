package api.istoki.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "feedback")
class FeedbackEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var feedbackId: Int = 0,
    @OneToOne
    @JoinColumn(name = "feedback_user")
    var feedbackUser: UserEntity,
    var feedbackDate: Date,
    var feedbackBody: String?,
    var feedbackMark: Int?
)