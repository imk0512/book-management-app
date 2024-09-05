package com.test.bookmanagementapp.dto.request.book

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateBookRequest(
    @field:NotBlank(message = "Title is required")
    @field:Size(max = 100, message = "Title must be less than 100 characters")
    @Schema(description = "Name of the title", example = "吾輩は猫である")
    val title: String,

    @field:NotBlank(message = "ISBN is required")
    @field:Pattern(regexp = "^[0-9]{3}-[0-9]{10}\$", message = "Invalid ISBN format")
    @Schema(description = "ISBN(国際標準図書番号：International Standard Book Number)", example = "9789233035058")
    val isbn: String,

    @field:NotNull(message = "Author ID is required")
    val authorId: Long
)
