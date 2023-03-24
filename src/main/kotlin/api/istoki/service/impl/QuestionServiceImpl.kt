package api.istoki.service.impl

import api.istoki.dto.QuestionDto
import api.istoki.entity.QuestionEntity
import api.istoki.repository.ExerciseRepository
import api.istoki.repository.QuestionRepository
import api.istoki.response.exception.NotFoundException
import api.istoki.service.QuestionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionServiceImpl(
    private val questionRepository: QuestionRepository,
    private val exerciseRepository: ExerciseRepository
) : QuestionService {
    override fun getAllQuestions(): List<QuestionDto> {
        return questionRepository.findByOrderByQueId().map { it.toDto() }
    }

    override fun getQuestionById(id: Int): QuestionDto {
        return questionRepository.findByIdOrNull(id)?.toDto()
            ?: throw NotFoundException("Question", id)
    }

    override fun createQuestion(dto: QuestionDto): String {
        questionRepository.save(dto.toEntity())
        return "Question successfully added"
    }

    override fun editQuestion(id: Int, dto: QuestionDto): String {
        val currQue = questionRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Question", id)

        currQue.queBody = dto.queBody ?: currQue.queBody
        currQue.queExercise = exerciseRepository.findByIdOrNull(dto.queExercise) ?: currQue.queExercise

        questionRepository.save(currQue)
        return "Question successfully updated"
    }

    override fun deleteQuestion(id: Int): String {
        questionRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Question", id)

        questionRepository.deleteById(id)
        return "Question Successfully deleted"
    }

    private fun QuestionEntity.toDto(): QuestionDto =
        QuestionDto(
            queId = this.queId,
            queBody = this.queBody,
            queExercise = this.queExercise.excId,
            queAnswers = this.queAnswers
        )

    private fun QuestionDto.toEntity(): QuestionEntity =
        QuestionEntity(
            queId = 0,
            queBody = this.queBody,
            queExercise = exerciseRepository.getByExcId(this.queExercise),
            queAnswers = this.queAnswers
        )
}