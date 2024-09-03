package com.test.bookmanagementapp.repository

import com.test.bookmanagementapp.model.Book
import com.test.bookmanagementapp.model.BookWithAuthor
import com.test.jooq.tables.Authors.AUTHORS
import com.test.jooq.tables.Books.BOOKS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class BookRepository(private val dsl: DSLContext) {

    fun findAll(): List<Book> {
        return dsl.selectFrom(BOOKS)
            .fetchInto(Book::class.java)
    }

    fun findById(id: Long): Book? {
        return dsl.selectFrom(BOOKS)
            .where(BOOKS.ID.eq(id))
            .fetchOneInto(Book::class.java)
    }


    fun findBooksByAuthorId(authorId: Long): List<Book> {
        return dsl.selectFrom(BOOKS)
            .where(BOOKS.AUTHOR_ID.eq(authorId))
            .fetchInto(Book::class.java)
    }

    fun markBooksAsDeletedByAuthorId(authorId: Long) {
        dsl.update(BOOKS)
            .set(BOOKS.DELETED_AT, LocalDateTime.now())
            .where(BOOKS.AUTHOR_ID.eq(authorId))
            .execute()
    }


    fun findByAuthorIds(authorIds: List<Long>): List<Book> {
        return dsl.selectFrom(BOOKS)
            .where(BOOKS.AUTHOR_ID.`in`(authorIds))
            .fetchInto(Book::class.java)
    }

    fun findBooksByAuthorName(authorName: String): List<BookWithAuthor> {
        return dsl.select(BOOKS.ID, BOOKS.TITLE, BOOKS.ISBN, AUTHORS.NAME)
            .from(BOOKS)
            .join(AUTHORS).on(BOOKS.AUTHOR_ID.eq(AUTHORS.ID))
            .where(AUTHORS.NAME.containsIgnoreCase(authorName))
            .fetchInto(BookWithAuthor::class.java)
    }


    fun save(book: Book): Book {
        return if (book.id == null) {
            // Insert new book
            val id = dsl.insertInto(BOOKS)
                .set(BOOKS.TITLE, book.title)
                .set(BOOKS.ISBN, book.isbn)
                .set(BOOKS.AUTHOR_ID, book.authorId)
                .returning(BOOKS.ID)
                .fetchOne()!!.id
            book.copy(id = id)
        } else {
            // Update existing book
            dsl.update(BOOKS)
                .set(BOOKS.TITLE, book.title)
                .set(BOOKS.ISBN, book.isbn)
                .set(BOOKS.AUTHOR_ID, book.authorId)
                .where(BOOKS.ID.eq(book.id))
                .execute()
            book
        }
    }

    fun deleteById(id: Long) {
        dsl.deleteFrom(BOOKS)
            .where(BOOKS.ID.eq(id))
            .execute()
    }
}
