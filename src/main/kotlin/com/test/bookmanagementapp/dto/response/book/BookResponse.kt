package com.test.bookmanagementapp.dto.response.book

data class BookResponse(
    val id: Long,
    val title: String,
    val isbn: String,
    val authorId: Long
)
