package com.test.bookmanagementapp.controller

import com.test.bookmanagementapp.dto.common.ApiResponse
import com.test.bookmanagementapp.dto.request.book.AuthorIdsRequest
import com.test.bookmanagementapp.dto.request.book.CreateBookRequest
import com.test.bookmanagementapp.dto.request.book.UpdateBookRequest
import com.test.bookmanagementapp.dto.response.book.BookResponse
import com.test.bookmanagementapp.dto.response.book.BookWithAuthorResponse
import com.test.bookmanagementapp.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {

    @GetMapping
    fun getAllBooks(): ResponseEntity<ApiResponse<List<BookResponse>>> {
        val books = bookService.getAllBooks().map { book ->
            BookResponse(
                id = book.id!!,
                title = book.title,
                isbn = book.isbn,
                authorId = book.authorId
            )
        }
        val response = ApiResponse(
            status = "success",
            data = books
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<ApiResponse<BookResponse>> {
        val book = bookService.getBookById(id)
        return if (book != null) {
            val response = BookResponse(
                id = book.id!!,
                title = book.title,
                isbn = book.isbn,
                authorId = book.authorId
            )
            ResponseEntity.ok(ApiResponse(status = "success", data = response))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse(status = "error", message = "Book not found"))
        }
    }

    @GetMapping("/search")
    fun getBooksByAuthorName(@RequestParam authorName: String): ResponseEntity<ApiResponse<List<BookWithAuthorResponse>>> {
        val booksWithAuthors = bookService.getBooksByAuthorName(authorName).map { bookWithAuthor ->
            BookWithAuthorResponse(
                id = bookWithAuthor.id,
                title = bookWithAuthor.title,
                isbn = bookWithAuthor.isbn,
                authorName = bookWithAuthor.authorName
            )
        }
        val response = ApiResponse(
            status = "success",
            data = booksWithAuthors
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/author/{authorId}")
    fun getBooksByAuthorId(@PathVariable authorId: Long): ResponseEntity<ApiResponse<List<BookResponse>>> {
        val books = bookService.getBooksByAuthorId(authorId).map { book ->
            BookResponse(
                id = book.id!!,
                title = book.title,
                isbn = book.isbn,
                authorId = book.authorId
            )
        }
        val response = ApiResponse(
            status = "success",
            data = books
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/search-by-author-ids")
    fun getBooksByAuthorIds(@RequestBody authorIdsRequest: AuthorIdsRequest): ResponseEntity<ApiResponse<List<BookResponse>>> {
        val books = bookService.getBooksByAuthorIds(authorIdsRequest.authorIds).map { book ->
            BookResponse(
                id = book.id!!,
                title = book.title,
                isbn = book.isbn,
                authorId = book.authorId
            )
        }
        val response = ApiResponse(
            status = "success",
            data = books
        )
        return ResponseEntity.ok(response)
    }


    @PostMapping
    fun createBook(@RequestBody createBookRequest: CreateBookRequest): ResponseEntity<ApiResponse<BookResponse>> {
        val book = bookService.createBook(
            title = createBookRequest.title,
            isbn = createBookRequest.isbn,
            authorId = createBookRequest.authorId
        )
        val response = BookResponse(
            id = book.id!!,
            title = book.title,
            isbn = book.isbn,
            authorId = book.authorId
        )
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse(status = "success", data = response))
    }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody updateBookRequest: UpdateBookRequest): ResponseEntity<ApiResponse<BookResponse>> {
        val updatedBook = bookService.updateBook(
            id = id,
            title = updateBookRequest.title,
            isbn = updateBookRequest.isbn,
            authorId = updateBookRequest.authorId
        )
        return if (updatedBook != null) {
            val response = BookResponse(
                id = updatedBook.id!!,
                title = updatedBook.title,
                isbn = updatedBook.isbn,
                authorId = updatedBook.authorId
            )
            ResponseEntity.ok(ApiResponse(status = "success", data = response))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse(status = "error", message = "Book not found"))
        }
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long): ResponseEntity<ApiResponse<Void>> {
        return try {
            bookService.deleteBookById(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse(status = "success"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(status = "error", message = "Failed to delete book"))
        }
    }
}
