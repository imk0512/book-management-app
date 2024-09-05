package com.test.bookmanagementapp.controller

import com.test.bookmanagementapp.dto.common.ApiResponse
import com.test.bookmanagementapp.dto.request.book.AuthorIdsRequest
import com.test.bookmanagementapp.dto.request.book.CreateBookRequest
import com.test.bookmanagementapp.dto.request.book.UpdateBookRequest
import com.test.bookmanagementapp.dto.response.book.BookResponse
import com.test.bookmanagementapp.dto.response.book.BookWithAuthorResponse
import com.test.bookmanagementapp.exception.ResourceNotFoundException
import com.test.bookmanagementapp.service.BookService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Book API", description = "API for managing Book")
@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {

    @Operation(summary = "全ての書籍を取得", description = "全ての書籍を取得します。削除された書籍は含まれません。")
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

    @Operation(summary = "IDで書籍を取得", description = "指定されたIDの書籍を取得します。")
    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Long): ResponseEntity<ApiResponse<BookResponse>> {
        val book = bookService.getBookById(id)?: throw ResourceNotFoundException("Book with id $id not found")
        val response = BookResponse(
            id = book.id!!,
            title = book.title,
            isbn = book.isbn,
            authorId = book.authorId
        )
        return ResponseEntity.ok(ApiResponse(status = "success", data = response))
    }

    @Operation(summary = "著者名で書籍を検索", description = "指定された著者名で部分一致検索を行い、関連する書籍を返します。")
    @GetMapping("/search")
    fun getBooksByAuthorName(@RequestParam authorName: String): ResponseEntity<ApiResponse<List<BookWithAuthorResponse?>>> {
        val booksWithAuthors = bookService.getBooksByAuthorName(authorName).map { bookWithAuthor ->
            bookWithAuthor.authorName?.let {
                BookWithAuthorResponse(
                    id = bookWithAuthor.id,
                    title = bookWithAuthor.title,
                    isbn = bookWithAuthor.isbn,
                    authorId = bookWithAuthor.authorId,
                    authorName = bookWithAuthor.authorName
                )
            }
        }
        val response = ApiResponse(
            status = "success",
            data = booksWithAuthors
        )
        return ResponseEntity.ok(response)
    }

    @Operation(summary = "著者IDで書籍を取得", description = "指定された著者IDに関連する書籍を取得します。")
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

    @Operation(summary = "複数の著者IDで書籍を取得", description = "指定された著者IDのリストに関連する書籍を取得します。")
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

    @Operation(summary = "新しい書籍を作成", description = "新しい書籍を登録します。")
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

    @Operation(summary = "書籍を更新", description = "既存の書籍の情報を更新します。")
    @PutMapping("/{id}")
    fun updateBook(@PathVariable id: Long, @RequestBody updateBookRequest: UpdateBookRequest): ResponseEntity<ApiResponse<BookResponse>> {
        val updatedBook = bookService.updateBook(
            id = id,
            title = updateBookRequest.title,
            isbn = updateBookRequest.isbn,
            authorId = updateBookRequest.authorId
        )

        val response = BookResponse(
            id = updatedBook.id!!,
            title = updatedBook.title,
            isbn = updatedBook.isbn,
            authorId = updatedBook.authorId
        )
        return ResponseEntity.ok(ApiResponse(status = "success", data = response))

    }

    @Operation(summary = "書籍を削除", description = "指定されたIDの書籍を削除します。削除後、削除済みの書籍は検索結果に表示されません。")
    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Long): ResponseEntity<Void> {
        bookService.deleteBookById(id)
        return ResponseEntity.noContent().build()
    }
}
