package com.test.bookmanagementapp.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class CreateAuthorRequest(
    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:NotNull(message = "Birthdate is required")
    val birthdate: LocalDate,

    @field:NotBlank(message = "Nationality is required")
    val nationality: String
)
