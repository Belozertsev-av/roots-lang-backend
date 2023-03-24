package api.istoki.dto

import api.istoki.entity.AnswerEntity

data class QuestionDto(
    val queId: Int? = null,
    val queBody: String?,
    val queExercise: Int,
    val queAnswers: List<AnswerEntity>
)
