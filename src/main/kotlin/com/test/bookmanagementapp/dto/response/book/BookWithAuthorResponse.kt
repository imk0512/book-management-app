package com.test.bookmanagementapp.dto.response.book

data class BookWithAuthorResponse(
    val id: Long,
    val title: String,
    val isbn: String,
    val authorId: Long,
    val authorName: String
)