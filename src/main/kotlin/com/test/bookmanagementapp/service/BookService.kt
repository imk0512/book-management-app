package com.test.bookmanagementapp.service

import com.test.bookmanagementapp.exception.ResourceNotFoundException
import com.test.bookmanagementapp.model.Book
import com.test.bookmanagementapp.model.BookWithAuthor
import com.test.bookmanagementapp.repository.AuthorRepository
import com.test.bookmanagementapp.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
) {

    fun getAllBooks(): List<Book> {
        return bookRepository.findAll()
    }

    fun getBookById(id: Long): Book? {
        return bookRepository.findById(id)
    }

    fun getBooksByAuthorId(authorId: Long): List<Book> {
        return bookRepository.findBooksByAuthorId(authorId)
    }

    fun getBooksByAuthorName(authorName: String): List<BookWithAuthor> {
        return bookRepository.findBooksByAuthorName(authorName)
    }

    fun getBooksByAuthorIds(authorIds: List<Long>): List<Book> {
        return bookRepository.findByAuthorIds(authorIds)
    }

    fun createBook(title: String, isbn: String, authorId: Long): Book {

        if (title.length > 100) {
            throw IllegalArgumentException("Title must be less than 100 characters")
        }

        authorRepository.findById(authorId)
            ?: throw ResourceNotFoundException("Author with ID $authorId not found")

        if (bookRepository.existsByIsbn(isbn)) {
            throw IllegalArgumentException("ISBN $isbn already exists. Please use a unique ISBN.")
        }

        val book = Book(
            id = null,
            title = title,
            isbn = isbn,
            authorId = authorId
        )
        return bookRepository.save(book)
    }



    fun updateBook(id: Long, title: String?, isbn: String?, authorId: Long?): Book? {
        if (title != null) {
            val trimmedTitle = title.trim()
            if (trimmedTitle.isEmpty()) {
                throw IllegalArgumentException("Title cannot be empty or whitespace only")
            }
            if (trimmedTitle.length > 255) {
                throw IllegalArgumentException("Title must be 255 characters or less")
            }
        }
        if (isbn != null && isbn.length != 13) {
            throw IllegalArgumentException("ISBN must be exactly 13 characters")
        }

        val existingBook = bookRepository.findById(id)
        return if (existingBook != null) {
            if (authorId != null && !authorRepository.existsById(authorId)) {
                throw ResourceNotFoundException("Author with id $authorId not found")
            }

            val updatedBook = existingBook.copy(
                title = title ?: existingBook.title,
                isbn = isbn ?: existingBook.isbn,
                authorId = authorId ?: existingBook.authorId
            )
            bookRepository.save(updatedBook)
        } else {
            throw ResourceNotFoundException("Book with id $id not found")
        }
    }


    fun deleteBookById(id: Long) {
        bookRepository.deleteById(id)
    }
}
