package api.istoki


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class IstokiApplication

fun main(args: Array<String>) {
    runApplication<IstokiApplication>(*args)
}
