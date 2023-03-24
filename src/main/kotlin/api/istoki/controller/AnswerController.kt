package api.istoki.controller

import api.istoki.dto.AnswerDto
import api.istoki.service.AnswerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/answers")
class AnswerController(private val answerService: AnswerService) {
    @GetMapping
    fun getAllAnswers(): List<AnswerDto> = answerService.getAllAnswers()

    @GetMapping("/{answerId}")
    fun getAnswer(@PathVariable("answerId") id: Int): AnswerDto = answerService.getAnswer(id)

    @PostMapping
    fun createAnswer(@RequestBody dto: AnswerDto): String = answerService.createAnswer(dto)

    @PatchMapping("/{answerId}")
    fun editAnswer(@PathVariable("answerId") id: Int, @RequestBody dto: AnswerDto): String =
        answerService.editAnswer(id, dto)

    @DeleteMapping("/{answerId}")
    fun deleteAnswer(@PathVariable("answerId") id: Int): String = answerService.deleteAnswer(id)
}