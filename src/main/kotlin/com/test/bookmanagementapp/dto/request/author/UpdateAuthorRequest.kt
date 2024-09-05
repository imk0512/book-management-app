package com.test.bookmanagementapp.dto.request.author

import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UpdateAuthorRequest(
    @field:Size(max = 255, message = "Name must be 255 characters or less")
    val name: String?,

    val birthdate: LocalDate?
)
