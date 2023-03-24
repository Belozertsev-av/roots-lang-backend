package api.istoki.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "exercises")
class ExerciseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val excId: Int = 0,
    var excTitle: String?,
    @ManyToOne
    @JoinColumn(name = "exc_lesson")
    @JsonIgnore
    var excLesson: LessonEntity,
    @OneToMany(mappedBy = "queExercise")
    val excQuestions: List<QuestionEntity>
)