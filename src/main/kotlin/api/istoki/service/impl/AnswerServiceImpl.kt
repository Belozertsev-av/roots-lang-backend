package api.istoki.service.impl

import api.istoki.dto.AnswerDto
import api.istoki.entity.AnswerEntity
import api.istoki.repository.AnswerRepository
import api.istoki.repository.QuestionRepository
import api.istoki.response.exception.NotFoundException
import api.istoki.service.AnswerService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AnswerServiceImpl(
    private val answerRepository: AnswerRepository,
    private val questionRepository: QuestionRepository
) : AnswerService {
    override fun getAllAnswers(): List<AnswerDto> {
        return answerRepository.findByOrderByAnswerId().map { it.toDto() }
    }

    override fun getAnswer(id: Int): AnswerDto {
        return answerRepository.findByIdOrNull(id)?.toDto()
            ?: throw NotFoundException("Answer", id)
    }

    override fun createAnswer(dto: AnswerDto): String {
        answerRepository.save(dto.toEntity())
        return "Answer successfully added"
    }

    override fun editAnswer(id: Int, dto: AnswerDto): String {
        val currAnswer = answerRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Answer", id)

        currAnswer.answerBody = dto.answerBody ?: currAnswer.answerBody
        currAnswer.answerQue = questionRepository.findByIdOrNull(dto.answerQue) ?: currAnswer.answerQue

        answerRepository.save(currAnswer)
        return "Answer successfully updated"
    }

    override fun deleteAnswer(id: Int): String {
        val currAnswer = answerRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Answer", id)
        answerRepository.deleteById(id)
        return "Answer successfully deleted"
    }

    private fun AnswerEntity.toDto(): AnswerDto =
        AnswerDto(
            answerId = this.answerId,
            answerBody = this.answerBody,
            answerQue = this.answerQue.queId,
        )

    private fun AnswerDto.toEntity(): AnswerEntity =
        AnswerEntity(
            answerId = 0,
            answerBody = this.answerBody,
            answerQue = questionRepository.getByQueId(this.answerQue)
        )
}