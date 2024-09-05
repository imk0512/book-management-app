package com.test.bookmanagementapp.dto.request.book

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class UpdateBookRequest(
    @field:Size(max = 255, message = "Title must be 255 characters or less")
    val title: String?,

    @field:Size(min = 13, max = 13, message = "ISBN must be exactly 13 characters")
    val isbn: String?,

    @field:Min(1, message = "Author ID must be greater than 0")
    val authorId: Long?
)

