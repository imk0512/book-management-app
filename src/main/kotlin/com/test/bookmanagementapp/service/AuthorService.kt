package com.test.bookmanagementapp.service

import com.test.bookmanagementapp.exception.ResourceNotFoundException
import com.test.bookmanagementapp.model.Author
import com.test.bookmanagementapp.repository.AuthorRepository
import com.test.bookmanagementapp.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service
@Transactional
class AuthorService(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository
) {

    fun getAllAuthors(): List<Author> {
        return authorRepository.findAll()
    }

    fun getAuthorById(id: Long): Author? {
        return authorRepository.findById(id)
    }

    fun createAuthor(name: String, birthdate: LocalDate): Author {
        val author = Author(
            id = null,
            name = name,
            birthdate = birthdate
        )
        return authorRepository.save(author)
    }

    fun updateAuthor(id: Long, name: String?, birthdate: LocalDate?): Author? {
        val existingAuthor = authorRepository.findById(id)
        return if (existingAuthor != null) {
            val updatedAuthor = existingAuthor.copy(
                name = name ?: existingAuthor.name,
                birthdate = birthdate ?: existingAuthor.birthdate
            )
            authorRepository.save(updatedAuthor)
        } else {
            null
        }
    }

    fun deleteAuthorById(id: Long) {
        val author = authorRepository.findById(id)
            ?: throw ResourceNotFoundException("Author with id $id not found")

        // 著者に関連する本を全て論理削除
        bookRepository.markBooksAsDeletedByAuthorId(id)

        val updatedAuthor = author.copy(deletedAt = LocalDateTime.now())
        authorRepository.save(updatedAuthor)
    }
    fun searchAuthors(name: String?, birthdate: String?): List<Author> {
        val authors = authorRepository.findAll()
        return authors.filter { author ->
            (name == null || author.name.contains(name, ignoreCase = true)) &&
                    (birthdate == null || author.birthdate.toString() == birthdate)
        }
    }
}
