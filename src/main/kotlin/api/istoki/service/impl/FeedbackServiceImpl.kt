package api.istoki.service.impl

import api.istoki.dto.FeedbackDto
import api.istoki.dto.UserFeedbackDto
import api.istoki.entity.FeedbackEntity
import api.istoki.entity.UserEntity
import api.istoki.repository.FeedbackRepository
import api.istoki.repository.UserRepository
import api.istoki.response.exception.NotFoundException
import api.istoki.service.FeedbackService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class FeedbackServiceImpl(
    private val feedbackRepository: FeedbackRepository,
    private val userRepository: UserRepository
) : FeedbackService {
    override fun getAll(page: Int): List<FeedbackDto> {
        return feedbackRepository.findByOrderByFeedbackId(PageRequest.of(page, 20)).map { it.toDto() }
    }

    override fun getAvgMark(): Double {
        return feedbackRepository.getAvgMark()
    }

    override fun getFeedbackById(id: Int): FeedbackDto {
        return feedbackRepository.findByUserId(id)!!.toDto()
    }

    override fun addFeedback(dto: FeedbackDto): String {
        feedbackRepository.save(dto.toEntity())
        return "Feedback is successfully posted"
    }

    override fun uploadFeedback(dto: FeedbackDto, id: Int): String {
        val feedback = feedbackRepository.findByUserId(id)?.toDto()
            ?: throw NotFoundException("Отзыв", id)

        feedback.feedbackId = dto.feedbackId
        feedback.feedbackUser = dto.feedbackUser
        feedback.feedbackBody = dto.feedbackBody ?: feedback.feedbackBody
        feedback.feedbackMark = dto.feedbackMark ?: feedback.feedbackMark
        feedback.feedbackDate = dto.feedbackDate

        feedbackRepository.save(feedback.toEntity())
        return "Feedback is successfully updated"
    }


    private fun FeedbackEntity.toDto(): FeedbackDto =
        FeedbackDto(
            feedbackId = this.feedbackId,
            feedbackUser = this.feedbackUser.toDto(),
            feedbackDate = this.feedbackDate,
            feedbackBody = this.feedbackBody,
            feedbackMark = this.feedbackMark
        )

    private fun FeedbackDto.toEntity(): FeedbackEntity =
        FeedbackEntity(
            feedbackId = this.feedbackId,
            feedbackUser = userRepository.getByUserId(this.feedbackUser.id),
            feedbackDate = this.feedbackDate,
            feedbackBody = this.feedbackBody,
            feedbackMark = this.feedbackMark
        )

    private fun UserEntity.toDto(): UserFeedbackDto =
        UserFeedbackDto(
            id = this.userId,
            firstName = this.userFirstName,
            lastName = this.userLastName,
            avatar = this.userAvatar,
        )
}