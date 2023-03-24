package api.istoki.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class ScoresDto(
    @JsonIgnore
    val scoresId: Int? = null,
    val scoresUser: Int,
    val scoresLanguage: Int,
    val scores: Int
)
