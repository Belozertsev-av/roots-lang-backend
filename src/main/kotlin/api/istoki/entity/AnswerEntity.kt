package api.istoki.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "answers")
class AnswerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answerId: Int = 0,
    var answerBody: String?,
    @ManyToOne
    @JoinColumn(name = "answer_que")
    @JsonIgnore
    var answerQue: QuestionEntity,
)