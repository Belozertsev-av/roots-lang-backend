package api.istoki.dto

data class LessonDto(
    val lessonId: Int? = null,
    val lessonTitle: String?,
    val lessonLanguage: Int,
    val lessonExc: List<ExerciseShortDto>
)
