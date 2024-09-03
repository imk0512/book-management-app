package com.test.bookmanagementapp.dto.request.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateBookRequest(
    @field:NotBlank(message = "Title is required")
    @field:Size(max = 100, message = "Title must be less than 100 characters")
    val title: String,

    @field:NotBlank(message = "ISBN is required")
    @field:Pattern(regexp = "^[0-9]{3}-[0-9]{10}\$", message = "Invalid ISBN format")
    val isbn: String,

    @field:NotNull(message = "Author ID is required")
    val authorId: Long
)
