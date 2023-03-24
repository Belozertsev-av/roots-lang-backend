package api.istoki.dto

import api.istoki.entity.QuestionEntity

data class ExerciseDto(
    val excId: Int? = null,
    val excTitle: String?,
    val excLesson: Int,
    val excQuestions: List<QuestionEntity>
)
