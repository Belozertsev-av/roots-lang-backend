package api.istoki.repository

import api.istoki.entity.LessonEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface LessonRepository : CrudRepository<LessonEntity, Int> {
    fun findByOrderByLessonId(): List<LessonEntity>

    @Query(value = "SELECT * FROM lessons WHERE lessons.lesson_language =:langId ", nativeQuery = true)
    fun findByLanguageId(langId: Int): List<LessonEntity>

    fun getByLessonId(lessonId: Int): LessonEntity
}