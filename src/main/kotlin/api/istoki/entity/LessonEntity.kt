package api.istoki.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "lessons")
class LessonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val lessonId: Int = 0,
    var lessonTitle: String?,
    @ManyToOne
    @JoinColumn(name = "lesson_language")
    @JsonIgnore
    var lessonLanguage: LanguageEntity,
    @OneToMany(mappedBy = "excLesson")
    val lessonExc: List<ExerciseEntity> = listOf()
)