package api.istoki.service.impl

import api.istoki.dto.ExerciseShortDto
import api.istoki.dto.LessonDto
import api.istoki.entity.ExerciseEntity
import api.istoki.entity.LessonEntity
import api.istoki.repository.LanguageRepository
import api.istoki.repository.LessonRepository
import api.istoki.response.exception.NotFoundException
import api.istoki.service.LessonService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LessonServiceImpl(
    private val lessonRepository: LessonRepository,
    private val languageRepository: LanguageRepository
) : LessonService {

    override fun getAllLessons(langId: Int): List<LessonDto> {
        return lessonRepository.findByLanguageId(langId).map { it.toDto() }
    }

    override fun getLessonById(lessonId: Int): LessonDto {
        return lessonRepository.findByIdOrNull(lessonId)?.toDto()
            ?: throw NotFoundException("Lesson", lessonId)
    }

    override fun createLesson(dto: LessonDto): String {
        lessonRepository.save(dto.toEntity())
        return "Lesson successfully added"
    }

    override fun editLesson(lessonId: Int, dto: LessonDto): String {
        val currLesson = lessonRepository.findByIdOrNull(lessonId)
            ?: throw NotFoundException("Lesson", lessonId)

        currLesson.lessonTitle = dto.lessonTitle ?: currLesson.lessonTitle
        currLesson.lessonLanguage = languageRepository.findByIdOrNull(dto.lessonLanguage) ?: currLesson.lessonLanguage

        lessonRepository.save(currLesson)
        return "Lesson successfully updated"
    }

    override fun deleteLesson(lessonId: Int): String {
        lessonRepository.findByIdOrNull(lessonId)
            ?: throw NotFoundException("Lesson", lessonId)

        lessonRepository.deleteById(lessonId)
        return "Lesson Successfully deleted"
    }

    private fun LessonEntity.toDto(): LessonDto =
        LessonDto(
            lessonId = this.lessonId,
            lessonTitle = this.lessonTitle,
            lessonLanguage = this.lessonLanguage.languageId,
            lessonExc = this.lessonExc.map { it.toDto() }
        )

    private fun ExerciseEntity.toDto(): ExerciseShortDto =
        ExerciseShortDto(
            excId = this.excId,
            excTitle = this.excTitle,
            excLesson = this.excLesson.lessonId
        )

    private fun LessonDto.toEntity(): LessonEntity =
        LessonEntity(
            lessonId = 0,
            lessonTitle = this.lessonTitle,
            lessonLanguage = languageRepository.getByLanguageId(this.lessonLanguage)
        )
}