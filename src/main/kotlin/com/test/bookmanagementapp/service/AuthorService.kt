package com.test.bookmanagementapp.service

import com.test.bookmanagementapp.model.Author
import com.test.bookmanagementapp.repository.AuthorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class AuthorService(private val authorRepository: AuthorRepository) {

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
        authorRepository.deleteById(id)
    }

    fun searchAuthors(name: String?, birthdate: String?): List<Author> {
        val authors = authorRepository.findAll()
        return authors.filter { author ->
            (name == null || author.name.contains(name, ignoreCase = true)) &&
                    (birthdate == null || author.birthdate.toString() == birthdate)
        }
    }
}
