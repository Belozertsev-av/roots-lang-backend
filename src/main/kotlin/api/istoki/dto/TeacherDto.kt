package api.istoki.dto

import api.istoki.entity.LanguageEntity
import com.fasterxml.jackson.annotation.JsonIgnore

data class TeacherDto(
    val id: Int? = null,
    val login: String?,
    @JsonIgnore
    val password: String?,
    val fName: String?,
    val lName: String?,
    val email: String?,
    val avatar: String,
    val phoneNumber: Long,
    @JsonIgnore
    val languages: List<LanguageEntity>
)
