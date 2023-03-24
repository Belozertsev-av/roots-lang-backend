package api.istoki.controller

import api.istoki.dto.FeedbackDto
import api.istoki.service.FeedbackService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/feedback")
class FeedbackController(private val feedbackService: FeedbackService) {

    @GetMapping
    fun getAll(@RequestParam("page") page: Int): List<FeedbackDto> = feedbackService.getAll(page)

    @GetMapping("/{id}")
    fun getFeedbackById(@PathVariable("id") id: Int): FeedbackDto = feedbackService.getFeedbackById(id)

    @PostMapping
    fun addFeedback(@RequestBody dto: FeedbackDto): String =
        feedbackService.addFeedback(dto)

    @CrossOrigin
    @PatchMapping("/{id}")
    fun uploadFeedback(@RequestBody dto: FeedbackDto, @PathVariable("id") id: Int): String =
        feedbackService.uploadFeedback(dto, id)
}