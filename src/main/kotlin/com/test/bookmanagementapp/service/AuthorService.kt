package com.test.bookmanagementapp.service

import com.test.bookmanagementapp.exception.ResourceNotFoundException
import com.test.bookmanagementapp.model.Author
import com.test.bookmanagementapp.repository.AuthorRepository
import com.test.bookmanagementapp.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class AuthorService(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository
) {
    private val logger = LoggerFactory.getLogger(AuthorService::class.java)

    fun getAllAuthors(): List<Author> {
        return authorRepository.findAll()
    }

    fun getAuthorById(id: Long): Author? {
        return authorRepository.findById(id)
    }

    fun createAuthor(name: String, birthdate: LocalDate): Author {

        val trimmedName = name.trim()
        if (trimmedName.isEmpty()) {
            throw IllegalArgumentException("Name cannot be empty or whitespace only")
        }
        if (trimmedName.length > 255) {
            throw IllegalArgumentException("Name must be 255 characters or less")
        }

        if (birthdate.isAfter(LocalDate.now())) {
            throw IllegalArgumentException("Birthdate cannot be in the future")
        }

        val author = Author(
            id = null,
            name = trimmedName,
            birthdate = birthdate
        )
        return authorRepository.save(author)
    }

    fun updateAuthor(id: Long, name: String?, birthdate: LocalDate?): Author {
        val existingAuthor = authorRepository.findById(id)
            ?: throw ResourceNotFoundException("Author with id $id not found")

        if (name != null) {
            val trimmedName = name.trim()
            if (trimmedName.isEmpty()) {
                throw IllegalArgumentException("Name cannot be empty or whitespace only")
            }
            if (trimmedName.length > 255) {
                throw IllegalArgumentException("Name must be 255 characters or less")
            }
        }

        if (birthdate != null && birthdate.isAfter(LocalDate.now())) {
            throw IllegalArgumentException("Birthdate cannot be in the future")
        }

        val updatedAuthor = existingAuthor.copy(
            name = name ?: existingAuthor.name,
            birthdate = birthdate ?: existingAuthor.birthdate
        )
        return authorRepository.save(updatedAuthor)
    }


    @Transactional(rollbackFor = [Exception::class])
    fun deleteAuthorById(id: Long) {
        authorRepository.findById(id)
            ?: throw ResourceNotFoundException("Author with id $id not found")

        try {
            // 著者に関連する本を全て論理削除
            bookRepository.markBooksAsDeletedByAuthorId(id)

            // 著者の論理削除
            authorRepository.deleteById(id)

            logger.info("Author with ID $id and their books have been successfully deleted.")
        } catch (e: Exception) {
            logger.error("Failed to delete author with ID $id: ${e.message}", e)
            throw e
        }
    }

    fun searchAuthors(name: String, birthdate: LocalDate?): List<Author> {
        return authorRepository.searchAuthors(name, birthdate)
    }
}

