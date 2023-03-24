package api.istoki.dto

import java.util.*

data class FeedbackDto(
    var feedbackId: Int = 0,
    var feedbackUser: UserFeedbackDto,
    var feedbackDate: Date,
    var feedbackBody: String?,
    var feedbackMark: Int?
)
