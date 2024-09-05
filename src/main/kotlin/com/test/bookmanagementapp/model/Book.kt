package com.test.bookmanagementapp.model

import java.time.LocalDateTime

data class Book(
    val id: Long?,
    val title: String,
    val isbn: String,
    val authorId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val deletedAt: LocalDateTime? = null
)
