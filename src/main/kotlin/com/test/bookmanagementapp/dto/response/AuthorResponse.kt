package com.test.bookmanagementapp.dto.response

import java.time.LocalDate
import java.time.LocalDateTime

data class AuthorResponse(
    val id: Long,
    val name: String,
    val birthdate: LocalDate,
    val nationality: String
//    val createdAt: LocalDateTime,
//    val updatedAt: LocalDateTime
)

