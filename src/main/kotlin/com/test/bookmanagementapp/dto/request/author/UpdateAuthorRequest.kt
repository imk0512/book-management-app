package com.test.bookmanagementapp.dto.request.author

import java.time.LocalDate


data class UpdateAuthorRequest(
    val name: String?,
    val birthdate: LocalDate?
)
