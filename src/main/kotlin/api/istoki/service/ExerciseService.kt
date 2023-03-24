package api.istoki.service

import api.istoki.dto.ExerciseDto

interface ExerciseService {

    fun getAllExc(lessonId: Int): List<ExerciseDto>

    fun getExcById(id: Int): ExerciseDto

    fun createExc(dto: ExerciseDto): String

    fun editExc(id: Int, dto: ExerciseDto): String

    fun deleteExc(id: Int): String
}