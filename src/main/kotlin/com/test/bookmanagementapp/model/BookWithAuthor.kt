package com.test.bookmanagementapp.model


data class BookWithAuthor(
    val id: Long,
    val title: String,
    val isbn: String,
    val authorId: Long,
    val authorName: String?
)


