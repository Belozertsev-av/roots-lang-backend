package api.istoki.repository

import api.istoki.entity.UserEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Int> {
    fun findByOrderByUserId(pageable: Pageable): List<UserEntity>

    fun findByUserFirstNameIgnoreCaseStartsWithOrderByUserFirstName(prefix: String): List<UserEntity>

    fun findByUserLoginIgnoreCaseOrderByUserLogin(login: String): UserEntity?

    fun findByUserMailIgnoreCaseOrderByUserMail(mail: String): UserEntity?

    fun findByUserPhoneOrderByUserPhone(tel: Long): UserEntity?

    fun findByUserLoginIgnoreCase(login: String): UserEntity?

    fun getByUserId(id: Int): UserEntity
}