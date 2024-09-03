package com.test.bookmanagementapp.dto.request.author

import io.swagger.v3.oas.annotations.media.Schema

data class SearchAuthorsRequest(
    @Schema(description = "Name of the author", example = "夏目漱石")
    val name: String? = null,

    @Schema(description = "Birthdate of the author", example = "1867-02-09")
    val birthdate: String? = null
)
