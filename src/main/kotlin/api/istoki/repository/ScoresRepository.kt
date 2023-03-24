package api.istoki.repository

import api.istoki.entity.LanguageEntity
import api.istoki.entity.ScoresEntity
import api.istoki.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface ScoresRepository : CrudRepository<ScoresEntity, Int> {

    fun getByScoresId(id: Int?): ScoresEntity

    fun getByScoresLanguageAndScoresUser(entity: LanguageEntity, user: UserEntity): List<ScoresEntity>

    fun deleteByScoresId(id: Int): ScoresEntity
}