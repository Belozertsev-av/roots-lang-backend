package api.istoki.service.impl

import api.istoki.dto.ExerciseDto
import api.istoki.entity.ExerciseEntity
import api.istoki.repository.ExerciseRepository
import api.istoki.repository.LessonRepository
import api.istoki.response.exception.NotFoundException
import api.istoki.service.ExerciseService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExerciseServiceImpl(
    private val exerciseRepository: ExerciseRepository,
    private val lessonRepository: LessonRepository,
) : ExerciseService {
    override fun getAllExc(lessonId: Int): List<ExerciseDto> {
        return exerciseRepository.findByLessonId(lessonId).map { it.toDto() }
    }

    override fun getExcById(id: Int): ExerciseDto {
        return exerciseRepository.findByIdOrNull(id)?.toDto()
            ?: throw NotFoundException("Exercise", id)
    }

    override fun createExc(dto: ExerciseDto): String {
        exerciseRepository.save(dto.toEntity())
        return "Exercise successfully added"
    }

    override fun editExc(id: Int, dto: ExerciseDto): String {
        val currExc = exerciseRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Exercise", id)

        currExc.excTitle = dto.excTitle ?: currExc.excTitle
        currExc.excLesson = lessonRepository.findByIdOrNull(dto.excLesson) ?: currExc.excLesson

        exerciseRepository.save(currExc)
        return "Exercise successfully updated"
    }

    override fun deleteExc(id: Int): String {
        exerciseRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Exercise", id)

        exerciseRepository.deleteById(id)
        return "Exercise Successfully deleted"
    }

    private fun ExerciseEntity.toDto(): ExerciseDto =
        ExerciseDto(
            excId = this.excId,
            excTitle = this.excTitle,
            excLesson = this.excLesson.lessonId,
            excQuestions = this.excQuestions
        )

    private fun ExerciseDto.toEntity(): ExerciseEntity =
        ExerciseEntity(
            excId = 0,
            excTitle = this.excTitle,
            excLesson = lessonRepository.getByLessonId(this.excLesson),
            excQuestions = this.excQuestions
        )
}