package api.istoki.controller

import api.istoki.dto.LoginDto
import api.istoki.dto.UserDto
import api.istoki.service.UserService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    // GET

    @GetMapping
    fun getAll(@RequestParam("page") page: Int): List<UserDto> = userService.getAll(page)

    @GetMapping("/{userId}")
    fun getById(@PathVariable("userId") userId: Int): UserDto = userService.findById(userId)

    @GetMapping("/search")
    fun getByParam(
        @RequestParam("login", required = false) login: String?,
        @RequestParam("mail", required = false) mail: String?,
        @RequestParam("tel", required = false) tel: Long?,
        @RequestParam("prefix", required = false) prefix: String?
    ): Any? =
        userService.findByParam(login, mail, tel, prefix)

    @GetMapping("/data")
    fun getUserData(@RequestHeader("authorization") jwt: String?): UserDto = userService.getUserData(jwt)

    // POST

    @CrossOrigin
    @PostMapping
    fun createUser(@RequestBody dto: UserDto): Int = userService.createUser(dto)

    @CrossOrigin
    @PostMapping("/login")
    fun login(@RequestBody dto: LoginDto, response: HttpServletResponse): String =
        userService.login(dto, response)

    @PostMapping("/logout")
    fun logout(@RequestBody jwt: String, response: HttpServletResponse): String = userService.logout(jwt, response)

    // PATCH

    @CrossOrigin
    @PatchMapping("/{userId}")
    fun editUser(
        @PathVariable("userId") userId: Int,
        @RequestParam("pass") pass: Boolean?,
        @RequestBody dto: UserDto
    ): String =
        userService.editUser(userId, pass, dto)

    @CrossOrigin
    @PutMapping("/{userId}/add")
    fun addLanguage(@RequestParam("language") langId: Int, @PathVariable("userId") userId: Int): String =
        userService.addLanguage(langId, userId)

    // DELETE

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable("userId") userId: Int) = userService.deleteUser(userId)


    @DeleteMapping("/{userId}/delete")
    fun deleteLanguage(@RequestParam("language") langId: Int, @PathVariable("userId") userId: Int): String =
        userService.deleteLanguage(langId, userId)
}