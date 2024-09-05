package com.test.bookmanagementapp.dto.request.author

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateAuthorRequest(
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 255, message = "Name must be 255 characters or less")
    @Schema(description = "Name of the author", example = "夏目漱石")
    val name: String,

    @field:NotNull(message = "Birthdate is required")
    @Schema(description = "Birthdate of the author", example = "1867-02-09")
    val birthdate: LocalDate
)
