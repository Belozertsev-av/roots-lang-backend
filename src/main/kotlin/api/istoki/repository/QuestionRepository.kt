package api.istoki.repository

import api.istoki.entity.QuestionEntity
import org.springframework.data.repository.CrudRepository

interface QuestionRepository : CrudRepository<QuestionEntity, Int> {
    fun findByOrderByQueId(): List<QuestionEntity>

    fun getByQueId(id: Int): QuestionEntity
}