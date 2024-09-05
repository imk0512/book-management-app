package com.test.bookmanagementapp.service

import com.test.bookmanagementapp.exception.ResourceNotFoundException
import com.test.bookmanagementapp.model.Author
import com.test.bookmanagementapp.repository.AuthorRepository
import com.test.bookmanagementapp.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

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


    fun deleteAuthorById(id: Long) {
        val author = authorRepository.findById(id)
            ?: throw ResourceNotFoundException("Author with id $id not found")

        // 著者に関連する本を全て論理削除
        bookRepository.markBooksAsDeletedByAuthorId(id)

        authorRepository.deleteById(id)
    }

    fun searchAuthors(name: String?, birthdate: LocalDate?): List<Author> {
        return authorRepository.searchAuthors(name, birthdate)
    }
}

