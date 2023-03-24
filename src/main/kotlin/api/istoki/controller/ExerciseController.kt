package api.istoki.controller

import api.istoki.dto.ExerciseDto
import api.istoki.service.ExerciseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/exercises")
class ExerciseController(private val exerciseService: ExerciseService) {
    @GetMapping
    fun getAllExc(@RequestParam("lessonId") lessonId: Int): List<ExerciseDto> = exerciseService.getAllExc(lessonId)


    @GetMapping("/{excId}")
    fun getExcById(@PathVariable excId: Int): ExerciseDto = exerciseService.getExcById(excId)

    @PostMapping
    fun createExc(@RequestBody dto: ExerciseDto): String = exerciseService.createExc(dto)

    @PatchMapping("/{excId}")
    fun editExc(@PathVariable("excId") id: Int, @RequestBody dto: ExerciseDto): String =
        exerciseService.editExc(id, dto)

    @DeleteMapping("/{excId}")
    fun deleteExc(@PathVariable("excId") id: Int): String = exerciseService.deleteExc(id)
}