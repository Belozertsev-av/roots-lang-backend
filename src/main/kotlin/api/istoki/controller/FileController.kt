package api.istoki.controller

import api.istoki.service.FileService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/files")
class FileController(private val fileService: FileService) {

    @CrossOrigin
    @PostMapping
    fun uploadFile(@RequestPart file: MultipartFile, @RequestParam("currAvatar") currAvatar: String): String =
        fileService.uploadFile(file, currAvatar)
}