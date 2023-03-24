package api.istoki.controller

import api.istoki.dto.LoginDto
import api.istoki.dto.TeacherDto
import api.istoki.service.TeacherService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/teachers")
class TeacherController(private val teacherService: TeacherService) {
    @GetMapping
    fun getAll(): List<TeacherDto> = teacherService.getAll()

    @GetMapping("/{teacherId}")
    fun getTeacherById(@PathVariable("teacherId") id: Int): TeacherDto = teacherService.getTeacherById(id)

    @PostMapping
    fun createTeacher(@RequestBody dto: TeacherDto): String = teacherService.createTeacher(dto)

    @PostMapping("/login")
    fun login(@RequestBody dto: LoginDto, response: HttpServletResponse): String =
        teacherService.login(dto, response)

    @GetMapping("/data")
    fun getTeacherData(@CookieValue("jwt") jwt: String?): TeacherDto = teacherService.getTeacherData(jwt)

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): String = teacherService.logout(response)
}