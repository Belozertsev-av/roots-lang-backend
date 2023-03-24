package api.istoki.controller

import api.istoki.dto.LanguageDto
import api.istoki.service.LanguageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/languages")
class LanguageController(private val languageService: LanguageService) {
    @GetMapping
    fun getAll(@RequestParam("list") list: Boolean): List<Any> = languageService.getAll(list)

    @GetMapping("/{langId}")
    fun getLanguageById(@PathVariable("langId") id: Int, @RequestParam("list") list: Boolean): Any? =
        languageService.getLanguageById(id, list)

    @PostMapping
    fun createLanguage(@RequestBody dto: LanguageDto): Int = languageService.createLanguage(dto)
}