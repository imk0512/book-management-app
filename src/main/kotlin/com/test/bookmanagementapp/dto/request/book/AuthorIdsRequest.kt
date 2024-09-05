package com.test.bookmanagementapp.dto.request.book

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

data class AuthorIdsRequest(

    @field:NotEmpty(message = "Author IDs must not be empty")
    @Schema(description = "Author IDs", example = "[1,2,3]")
    val authorIds: List<Long>
)
