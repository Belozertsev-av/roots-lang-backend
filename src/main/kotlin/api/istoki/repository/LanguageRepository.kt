package api.istoki.repository

import api.istoki.entity.LanguageEntity
import org.springframework.data.repository.CrudRepository

interface LanguageRepository : CrudRepository<LanguageEntity, Int> {
    fun findByOrderByLanguageId(): List<LanguageEntity>

    fun getByLanguageId(id: Int): LanguageEntity

}