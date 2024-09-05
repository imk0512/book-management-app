package com.test.bookmanagementapp.service

import com.test.bookmanagementapp.exception.ResourceNotFoundException
import com.test.bookmanagementapp.model.Author
import com.test.bookmanagementapp.model.Book
import com.test.bookmanagementapp.repository.AuthorRepository
import com.test.bookmanagementapp.repository.BookRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class AuthorServiceTest {

    @Mock
    private lateinit var authorRepository: AuthorRepository

    @Mock
    private lateinit var bookRepository: BookRepository

    @InjectMocks
    private lateinit var authorService: AuthorService

    @Test
    fun `deleteAuthorById - successful deletion`() {
        // Arrange
        val authorId = 1L
        val author = Author(id = authorId, name = "Test Author", birthdate = LocalDate.now())
        `when`(authorRepository.findById(authorId)).thenReturn(author)

        // Act
        authorService.deleteAuthorById(authorId)

        // Assert
        verify(bookRepository, times(1)).markBooksAsDeletedByAuthorId(authorId)
        verify(authorRepository, times(1)).deleteById(authorId)
    }

    @Test
    fun `deleteAuthorById - author not found`() {
        // Arrange
        val authorId = 1L
        `when`(authorRepository.findById(authorId)).thenReturn(null)

        // Act & Assert
        val exception = assertThrows<ResourceNotFoundException> {
            authorService.deleteAuthorById(authorId)
        }

        assertEquals("Author with id $authorId not found", exception.message)
        verify(bookRepository, never()).markBooksAsDeletedByAuthorId(authorId)
        verify(authorRepository, never()).deleteById(authorId)
    }

    @Test
    fun `deleteAuthorById - error during book deletion, rollback should occur`() {
        // Arrange
        val authorId = 1L
        val author = Author(id = authorId, name = "Test Author", birthdate = LocalDate.now())
        `when`(authorRepository.findById(authorId)).thenReturn(author)
        `when`(bookRepository.markBooksAsDeletedByAuthorId(authorId)).thenThrow(RuntimeException("Book deletion failed"))

        // Act & Assert
        val exception = assertThrows<RuntimeException> {
            authorService.deleteAuthorById(authorId)
        }

        assertEquals("Book deletion failed", exception.message)
        verify(bookRepository, times(1)).markBooksAsDeletedByAuthorId(authorId)
        verify(authorRepository, never()).deleteById(authorId) // ロールバックによって著者は削除されない
    }
}
