package api.istoki.service.impl

import api.istoki.dto.LanguageDto
import api.istoki.dto.LanguageListDto
import api.istoki.entity.LanguageEntity
import api.istoki.repository.LanguageRepository
import api.istoki.repository.TeacherRepository
import api.istoki.response.exception.NotFoundException
import api.istoki.service.LanguageService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LanguageServiceImpl(
    private val languageRepository: LanguageRepository,
    private val teacherRepository: TeacherRepository
) : LanguageService {
    override fun getAll(list: Boolean): List<Any> {
        return if (list) {
            languageRepository.findByOrderByLanguageId().map { it.toListDto() }
        } else {
            languageRepository.findByOrderByLanguageId().map { it.toDto() }
        }

    }

    override fun getLanguageById(id: Int, list: Boolean): Any? {
        return if (list) {
            languageRepository.findByIdOrNull(id)?.toListDto()
        } else if (!list) {
            languageRepository.findByIdOrNull(id)?.toDto()
        } else {
            throw NotFoundException("Language", id)
        }
    }

    override fun createLanguage(dto: LanguageDto): Int {
        return languageRepository.save(dto.toEntity()).languageId
    }

    private fun LanguageEntity.toDto(): LanguageDto =
        LanguageDto(
            languageId = this.languageId,
            languageTitle = this.languageTitle,
            languageFlag = this.languageFlag,
            languageCountOfUsers = this.languageUsers.size,
            languageLessons = this.languageLessons,
            languageTeacher = this.languageTeacher.teacherId,
        )

    private fun LanguageEntity.toListDto(): LanguageListDto =
        LanguageListDto(
            languageId = this.languageId,
            languageTitle = this.languageTitle,
            languageFlag = this.languageFlag,
        )

    private fun LanguageDto.toEntity(): LanguageEntity =
        LanguageEntity(
            languageId = 0,
            languageTitle = this.languageTitle,
            languageFlag = this.languageFlag,
            languageLessons = this.languageLessons,
            languageTeacher = teacherRepository.getByTeacherId(this.languageTeacher),
        )
}