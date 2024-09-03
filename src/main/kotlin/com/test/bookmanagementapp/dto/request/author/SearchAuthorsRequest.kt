package com.test.bookmanagementapp.dto.request.author

data class SearchAuthorsRequest(
    val name: String? = null,
    val nationality: String? = null,
    val birthdate: String? = null
)
