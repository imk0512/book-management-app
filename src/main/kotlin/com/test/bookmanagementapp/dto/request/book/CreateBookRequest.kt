package com.test.bookmanagementapp.dto.request.book

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateBookRequest(
    @field:NotBlank(message = "Title is required")
    @field:Size(max = 100, message = "Title must be less than 100 characters")
    @Schema(description = "Title of the book", example = "吾輩は猫である")
    val title: String,

    @field:NotBlank(message = "ISBN is required")
    @field:Pattern(regexp = "^[0-9]{13}$", message = "Invalid ISBN format. Must be 13 digits")
    @Schema(description = "ISBN (International Standard Book Number)", example = "9789233035058")
    val isbn: String,

    @field:NotNull(message = "Author ID is required")
    @Schema(description = "Author ID", example = "1")
    val authorId: Long
)
