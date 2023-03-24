package api.istoki.methods

/*

class methods {
    fun login(user: Any) {
        if (!user.comparePassword(dto.password)) {
            throw PasswordNotFoundException()
        }
        val jwt = Jwts.builder()
            .setIssuer(user.userId.toString())
            .setExpiration(Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
            .signWith(SignatureAlgorithm.HS512, "N3QQIr3V").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)

        return "You successfully logged in"
    }
}*/
