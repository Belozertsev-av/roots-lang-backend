package api.istoki.controller

import api.istoki.dto.LessonDto
import api.istoki.service.LessonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lessons")
class LessonController(private val lessonService: LessonService) {
    @GetMapping
    fun getAllLessons(@RequestParam("languageId") langId: Int): List<LessonDto> = lessonService.getAllLessons(langId)

    @GetMapping("/{lessonId}")
    fun getLessonById(@PathVariable lessonId: Int): LessonDto = lessonService.getLessonById(lessonId)

    @PostMapping
    fun createLesson(@RequestBody dto: LessonDto): String = lessonService.createLesson(dto)

    @PatchMapping("/{lessonId}")
    fun editLesson(@PathVariable("lessonId") id: Int, @RequestBody dto: LessonDto): String =
        lessonService.editLesson(id, dto)

    @DeleteMapping("/{lessonId}")
    fun deleteLesson(@PathVariable("lessonId") id: Int): String = lessonService.deleteLesson(id)
}