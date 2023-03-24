package api.istoki.service

import api.istoki.dto.QuestionDto

interface QuestionService {
    fun getAllQuestions(): List<QuestionDto>

    fun getQuestionById(id: Int): QuestionDto

    fun createQuestion(dto: QuestionDto): String

    fun editQuestion(id: Int, dto: QuestionDto): String

    fun deleteQuestion(id: Int): String
}