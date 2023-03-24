package api.istoki.repository

import api.istoki.entity.ExerciseEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ExerciseRepository : CrudRepository<ExerciseEntity, Int> {
    fun findByOrderByExcId(): List<ExerciseEntity>

    @Query(value = "SELECT * FROM exercises WHERE exercises.exc_lesson =:lessonId ", nativeQuery = true)
    fun findByLessonId(lessonId: Int): List<ExerciseEntity>

    fun getByExcId(excId: Int): ExerciseEntity
}