package api.istoki.dto

import api.istoki.entity.LessonEntity

data class LanguageDto(
    val languageId: Int? = null,
    val languageTitle: String,
    val languageCountOfUsers: Int,
    val languageLessons: List<LessonEntity>,
    val languageTeacher: Int,
    val languageFlag: String,
)
