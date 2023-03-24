package api.istoki.repository

import api.istoki.entity.TeacherEntity
import org.springframework.data.repository.CrudRepository

interface TeacherRepository : CrudRepository<TeacherEntity, Int> {
    fun findByOrderByTeacherId(): List<TeacherEntity>

    fun findByTeacherLogin(email: String): TeacherEntity?

    fun getByTeacherId(id: Int): TeacherEntity
}