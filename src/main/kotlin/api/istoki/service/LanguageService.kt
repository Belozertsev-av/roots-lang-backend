package api.istoki.service

import api.istoki.dto.LanguageDto

interface LanguageService {
    fun getAll(list: Boolean): List<Any>

    fun getLanguageById(id: Int, list: Boolean): Any?

    fun createLanguage(dto: LanguageDto): Int
}