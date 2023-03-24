package api.istoki.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "questions")
class QuestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val queId: Int = 0,
    var queBody: String?,
    @ManyToOne
    @JoinColumn(name = "que_exercise")
    @JsonIgnore
    var queExercise: ExerciseEntity,
    @OneToMany(mappedBy = "answerQue")
    val queAnswers: List<AnswerEntity>
)