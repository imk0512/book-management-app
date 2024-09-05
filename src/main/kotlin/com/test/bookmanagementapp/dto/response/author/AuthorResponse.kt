package com.test.bookmanagementapp.dto.response.author

import java.time.LocalDate

data class AuthorResponse(
    val id: Long,
    val name: String,
    val birthdate: LocalDate
)

