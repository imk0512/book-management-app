package com.test.bookmanagementapp.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Author(
    val id: Long?,
    val name: String,
    val birthdate: LocalDate,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val deletedAt: LocalDateTime? = null
)
