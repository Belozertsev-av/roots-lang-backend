package api.istoki.service

import api.istoki.dto.FeedbackDto

interface FeedbackService {

    fun getAll(page: Int): List<FeedbackDto>

    fun getAvgMark(): Double
    fun getFeedbackById(id: Int): FeedbackDto

    fun addFeedback(dto: FeedbackDto): String

    fun uploadFeedback(dto: FeedbackDto, id: Int): String
}