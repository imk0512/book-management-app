package com.test.bookmanagementapp.dto.request.author

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class SearchAuthorsRequest(

    @NotBlank(message = "Name cannot be blank")
    @Schema(description = "Name of the author", example = "夏目漱石")
    val name: String,

    @Schema(description = "Birthdate of the author", example = "1867-02-09")
    val birthdate: LocalDate? = null
)
