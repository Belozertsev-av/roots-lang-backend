# Back-end часть проекта Roots-Lang

Был реализован Restfull сервис на Spring Boot с использованием языка программирования Kotlin
для доступа к данным через API.

#### Для удобства навигации по проекту:

Для каждого обьекта данных созданы свои контроллеры, репозитории и сервисный слой, именованный в соответствии с объектом
данных

* Настройки, зависимости, плагины и репозитории можно посмотреть в файле `build.gradle.kts`
* Настройки подключения БД и JPA описаны в файле `application.yaml`
* Настройки Cors реализованы в классе `WebConfig`
