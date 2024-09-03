package com.test.bookmanagementapp.dto.request.book

import jakarta.validation.constraints.Size

data class UpdateBookRequest(
    @field:Size(max = 255, message = "Title must be 255 characters or less")
    val title: String?,

    @field:Size(min = 13, max = 13, message = "ISBN must be exactly 13 characters")
    val isbn: String?,

    val authorId: Long?
)
