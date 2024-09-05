package com.test.bookmanagementapp.dto.request.author

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import java.time.LocalDate

data class SearchAuthorsRequest(
    @Schema(description = "Name of the author", example = "夏目漱石")
    val name: String? = null,

    @field:Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Use YYYY-MM-DD.")
    @Schema(description = "Birthdate of the author", example = "1867-02-09")
    val birthdate: LocalDate? = null
)
