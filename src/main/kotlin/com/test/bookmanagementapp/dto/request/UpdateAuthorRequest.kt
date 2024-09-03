package com.test.bookmanagementapp.dto.request

import java.time.LocalDate


data class UpdateAuthorRequest(
    val name: String?,
    val birthdate: LocalDate?
)
