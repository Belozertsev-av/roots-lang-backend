package api.istoki.controller

import api.istoki.dto.QuestionDto
import api.istoki.service.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/questions")
class QuestionController(private val questionService: QuestionService) {
    @GetMapping
    fun getAllQuestions(): List<QuestionDto> = questionService.getAllQuestions()

    @GetMapping("/{queId}")
    fun getQuestionById(@PathVariable queId: Int): QuestionDto = questionService.getQuestionById(queId)

    @PostMapping
    fun createQuestion(@RequestBody dto: QuestionDto): String = questionService.createQuestion(dto)

    @PatchMapping("/{queId}")
    fun editQuestion(@PathVariable("queId") id: Int, @RequestBody dto: QuestionDto): String =
        questionService.editQuestion(id, dto)

    @DeleteMapping("/{queId}")
    fun deleteQuestion(@PathVariable("queId") id: Int): String = questionService.deleteQuestion(id)
}