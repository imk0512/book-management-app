package com.test.bookmanagementapp.repository

import com.test.bookmanagementapp.model.Book
import com.test.bookmanagementapp.model.BookWithAuthor
import com.test.jooq.tables.Authors.AUTHORS
import com.test.jooq.tables.Books.BOOKS
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class BookRepository(private val dsl: DSLContext) {

    private val logger = LoggerFactory.getLogger(BookRepository::class.java)

    fun findAll(): List<Book> {
        return dsl.selectFrom(BOOKS)
            .where(BOOKS.DELETED_AT.isNull)
            .fetchInto(Book::class.java)
    }

    fun findById(id: Long): Book? {
        return dsl.selectFrom(BOOKS)
            .where(BOOKS.ID.eq(id))
            .and(BOOKS.DELETED_AT.isNull)
            .fetchOneInto(Book::class.java)
    }

    fun findBooksByAuthorId(authorId: Long): List<Book> {
        return dsl.selectFrom(BOOKS)
            .where(BOOKS.AUTHOR_ID.eq(authorId))
            .and(BOOKS.DELETED_AT.isNull)
            .fetchInto(Book::class.java)
    }

    fun markBooksAsDeletedByAuthorId(authorId: Long) {
        dsl.update(BOOKS)
            .set(BOOKS.DELETED_AT, LocalDateTime.now())
            .where(BOOKS.AUTHOR_ID.eq(authorId))
            .and(BOOKS.DELETED_AT.isNull)
            .execute()
    }

    fun findByAuthorIds(authorIds: List<Long>): List<Book> {
        return dsl.selectFrom(BOOKS)
            .where(BOOKS.AUTHOR_ID.`in`(authorIds))
            .and(BOOKS.DELETED_AT.isNull)
            .fetchInto(Book::class.java)
    }

    fun findBooksByAuthorName(authorName: String): List<BookWithAuthor> {

        val trimmedName = authorName.replace("[\\s　]+".toRegex(), "")
        logger.info("Searching books for trimmed author name: $trimmedName")

        val result = dsl.select(
            BOOKS.ID,
            BOOKS.TITLE,
            BOOKS.ISBN,
            AUTHORS.ID.`as`("authorId"),
            AUTHORS.NAME.`as`("authorName")
        )
            .from(BOOKS)
            .innerJoin(AUTHORS).on(BOOKS.AUTHOR_ID.eq(AUTHORS.ID))
            .where(DSL.condition("REGEXP_REPLACE({0}, '[\\s　]+', '', 'g') ILIKE {1}", AUTHORS.NAME, "%$trimmedName%"))
            .and(AUTHORS.DELETED_AT.isNull)
            .and(BOOKS.DELETED_AT.isNull)
            .fetchInto(BookWithAuthor::class.java)
            .filterNotNull()

        logger.info("Search results: $result")

        return result
    }



    fun save(book: Book): Book {
        return if (book.id == null) {
            // 新しい書籍の登録
            val id = dsl.insertInto(BOOKS)
                .set(BOOKS.TITLE, book.title)
                .set(BOOKS.ISBN, book.isbn)
                .set(BOOKS.AUTHOR_ID, book.authorId)
                .returning(BOOKS.ID)
                .fetchOne()!!.id
            book.copy(id = id)
        } else {
            // 既存の書籍の更新
            dsl.update(BOOKS)
                .set(BOOKS.TITLE, book.title)
                .set(BOOKS.ISBN, book.isbn)
                .set(BOOKS.AUTHOR_ID, book.authorId)
                .where(BOOKS.ID.eq(book.id))
                .and(BOOKS.DELETED_AT.isNull)
                .execute()
            book
        }
    }


    fun deleteById(id: Long) {
        dsl.update(BOOKS)
            .set(BOOKS.DELETED_AT, LocalDateTime.now())
            .where(BOOKS.ID.eq(id))
            .and(BOOKS.DELETED_AT.isNull)
            .execute()
    }
}
