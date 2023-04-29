package api.istoki.repository

import api.istoki.entity.FeedbackEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FeedbackRepository : CrudRepository<FeedbackEntity, Int> {

    @Query(value = "SELECT * FROM feedback WHERE feedback_user=:Id ", nativeQuery = true)
    fun findByUserId(Id: Int?): FeedbackEntity?

    @Query(value = "SELECT * FROM feedback WHERE feedback_user=:Id ", nativeQuery = true)
    fun getByUserId(Id: Int?): FeedbackEntity?

    @Query(value = "SELECT AVG(feedback_mark) FROM feedback", nativeQuery = true)
    fun getAvgMark(): Double

    fun findByOrderByFeedbackId(pageable: Pageable): List<FeedbackEntity>
}