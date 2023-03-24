package api.istoki.repository

import api.istoki.entity.AnswerEntity
import org.springframework.data.repository.CrudRepository

interface AnswerRepository : CrudRepository<AnswerEntity, Int> {
    fun findByOrderByAnswerId(): List<AnswerEntity>
}