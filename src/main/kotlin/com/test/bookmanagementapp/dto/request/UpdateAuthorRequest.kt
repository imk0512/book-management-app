package com.test.bookmanagementapp.dto.request

import java.time.LocalDate
import jakarta.validation.constraints.NotBlank

data class UpdateAuthorRequest(
    @field:NotBlank
    val name: String? = null,

    val birthdate: LocalDate? = null,

    @field:NotBlank
    val nationality: String? = null
)
