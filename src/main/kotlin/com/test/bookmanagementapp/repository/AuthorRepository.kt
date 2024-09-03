package com.test.bookmanagementapp.repository

import com.test.jooq.tables.Authors.AUTHORS
import com.test.bookmanagementapp.model.Author
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class AuthorRepository(private val dsl: DSLContext) {

    fun findAll(): List<Author> {
        return dsl.selectFrom(AUTHORS)
            .fetchInto(Author::class.java)
    }

    fun findById(id: Long): Author? {
        return dsl.selectFrom(AUTHORS)
            .where(AUTHORS.ID.eq(id))
            .fetchOneInto(Author::class.java)
    }

    fun save(author: Author): Author {
        val currentTime = LocalDateTime.now()

        return if (author.id == null) {
            // 新しい著者の挿入
            val id = dsl.insertInto(AUTHORS)
                .set(AUTHORS.NAME, author.name)
                .set(AUTHORS.BIRTHDATE, author.birthdate)
                .set(AUTHORS.CREATED_AT, currentTime)
                .set(AUTHORS.UPDATED_AT, currentTime)
                .returning(AUTHORS.ID)
                .fetchOne()!!.id
            author.copy(id = id, createdAt = currentTime, updatedAt = currentTime)
        } else {
            // 既存の著者の更新
            dsl.update(AUTHORS)
                .set(AUTHORS.NAME, author.name)
                .set(AUTHORS.BIRTHDATE, author.birthdate)
                .set(AUTHORS.UPDATED_AT, currentTime)
                .where(AUTHORS.ID.eq(author.id))
                .execute()
            author.copy(updatedAt = currentTime)
        }
    }

    fun deleteById(id: Long) {
        val currentTime = LocalDateTime.now()

        dsl.update(AUTHORS)
            .set(AUTHORS.DELETED_AT, currentTime)
            .where(AUTHORS.ID.eq(id))
            .execute()
    }
}
