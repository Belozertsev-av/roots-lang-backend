package api.istoki.service.impl

import api.istoki.service.FileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Service
class FileServiceImpl : FileService {
    override fun uploadFile(file: MultipartFile, currAvatar: String): String {
        val fileName: String =
            "avatar" + System.currentTimeMillis()
                .toString() + file.originalFilename!!.substring(file.originalFilename!!.lastIndexOf('.'))
        val filePath: String =
            "D:\\Projects\\project\\istoki-frontend\\src\\assets\\img\\avatars" + File.separator + fileName
        val newFile = File(filePath)
        Files.copy(file.inputStream, Paths.get(filePath))

        if (currAvatar != "default_icon.png") {
            val fileToDeletePath: Path =
                Paths.get("D:\\Projects\\project\\istoki-frontend\\src\\assets\\img\\avatars" + File.separator + currAvatar)
            Files.delete(fileToDeletePath)
        }

        return fileName;
    }
}