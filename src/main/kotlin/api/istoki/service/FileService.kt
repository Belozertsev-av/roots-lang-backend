package api.istoki.service

import org.springframework.web.multipart.MultipartFile

interface FileService {

    fun uploadFile(file: MultipartFile, currAvatar: String): String

}