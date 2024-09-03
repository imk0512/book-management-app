package com.test.bookmanagementapp.dto.request.book

import jakarta.validation.constraints.NotEmpty

data class AuthorIdsRequest(
    @field:NotEmpty(message = "Author IDs must not be empty")
    val authorIds: List<Long>
)
