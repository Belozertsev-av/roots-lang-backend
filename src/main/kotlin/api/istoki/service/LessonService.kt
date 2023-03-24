package api.istoki.service

import api.istoki.dto.LessonDto

interface LessonService {
    fun getAllLessons(langId: Int): List<LessonDto>

    fun getLessonById(lessonId: Int): LessonDto

    fun createLesson(dto: LessonDto): String

    fun editLesson(lessonId: Int, dto: LessonDto): String

    fun deleteLesson(lessonId: Int): String
}