package api.istoki.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "languages")
class LanguageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val languageId: Int = 0,
    val languageTitle: String,
    @OneToMany(mappedBy = "scoresLanguage")
    @JsonIgnore
    var languageUsers: List<ScoresEntity> = listOf(),
    @OneToMany(mappedBy = "lessonLanguage")
    val languageLessons: List<LessonEntity> = listOf(),
    @ManyToOne
    @JoinColumn(name = "language_teacher")
    @JsonIgnore
    val languageTeacher: TeacherEntity,
    val languageFlag: String,
)