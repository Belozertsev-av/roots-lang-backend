# Back-end часть проекта Roots-Lang

Был реализован Restfull сервис на Spring Boot с использованием языка программирования Kotlin
для доступа к данным через API.

#### Для удобства навигации по проекту:

Для каждого обьекта данных созданы свои контроллеры, репозитории и сервисный слой, именованный в соответствии с объектом
данных

* Настройки, зависимости, плагины и репозитории можно посмотреть в файле `build.gradle.kts`
* Настройки подключения БД и JPA описаны в файле `application.yaml`
* Настройки Cors реализованы в классе `WebConfig`

### Реализованные эндпоинты

#### GET

* /teachers
* /teachers/1
* /teachers/data
* /api/users?page=0
* /api//users/25
* /api/users/search?login=Ivan.Ov (?prefix, ?mail, ?tel)
* /api/users/data
* /api/lessons
* /api/lessons/1
* /api/exercises
* /api/exercises/1
* /api/questions
* /api/questions/1
* /api/answers
* /api/answers/1

#### POST

* /api/users
* /api/users/login
* /api/languages
* /api/teachers
* /api/teachers/login
* /api/lessons
* /api/exercises
* /api/questions
* /api/answers
* /api/files

#### PATCH

* /api/users/26
* /api/lessons/1
* /api/exercises/1
* /api/questions/1
* /api/answers/1
* /api/feedback/26

#### PUT

* /users/20/add?language=2

#### DELETE

* /api/users/44
* /api/users/20/delete?language=2
* /api/lessons/1
* /api/exercises/1
* /api/questions/1
* /api/answers/1
