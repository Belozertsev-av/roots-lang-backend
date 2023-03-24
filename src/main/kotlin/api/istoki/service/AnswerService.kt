package api.istoki.service

import api.istoki.dto.AnswerDto

interface AnswerService {
    fun getAllAnswers(): List<AnswerDto>

    fun getAnswer(id: Int): AnswerDto

    fun createAnswer(dto: AnswerDto): String

    fun editAnswer(id: Int, dto: AnswerDto): String

    fun deleteAnswer(id: Int): String
}