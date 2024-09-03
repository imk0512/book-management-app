package com.test.bookmanagementapp.controller

import com.test.bookmanagementapp.dto.common.ApiResponse
import com.test.bookmanagementapp.dto.request.*
import com.test.bookmanagementapp.dto.response.*
import com.test.bookmanagementapp.service.AuthorService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/authors")
class AuthorController(private val authorService: AuthorService) {

    @GetMapping
    fun getAllAuthors(): ResponseEntity<ApiResponse<List<AuthorResponse>>> {
        val authors = authorService.getAllAuthors().map { author ->
            AuthorResponse(
                id = author.id!!,
                name = author.name,
                birthdate = author.birthdate,
                nationality = author.nationality
            )
        }
        val response = ApiResponse(
            status = "success",
            data = authors
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getAuthorById(@PathVariable id: Long): ResponseEntity<ApiResponse<AuthorResponse>> {
        val author = authorService.getAuthorById(id)
        return if (author != null) {
            val response = AuthorResponse(
                id = author.id!!,
                name = author.name,
                birthdate = author.birthdate,
                nationality = author.nationality
            )
            ResponseEntity.ok(ApiResponse(status = "success", data = response))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse(status = "error", message = "Author not found"))
        }
    }

    @PostMapping
    fun createAuthor(@RequestBody createAuthorRequest: CreateAuthorRequest): ResponseEntity<ApiResponse<AuthorResponse>> {
        val author = authorService.createAuthor(
            name = createAuthorRequest.name,
            birthdate = createAuthorRequest.birthdate,
            nationality = createAuthorRequest.nationality
        )
        val response = AuthorResponse(
            id = author.id!!,
            name = author.name,
            birthdate = author.birthdate,
            nationality = author.nationality
        )
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse(status = "success", data = response))
    }

    @PutMapping("/{id}")
    fun updateAuthor(@PathVariable id: Long, @RequestBody updateAuthorRequest: UpdateAuthorRequest): ResponseEntity<ApiResponse<AuthorResponse>> {
        val updatedAuthor = authorService.updateAuthor(
            id = id,
            name = updateAuthorRequest.name,
            birthdate = updateAuthorRequest.birthdate,
            nationality = updateAuthorRequest.nationality
        )
        return if (updatedAuthor != null) {
            val response = AuthorResponse(
                id = updatedAuthor.id!!,
                name = updatedAuthor.name,
                birthdate = updatedAuthor.birthdate,
                nationality = updatedAuthor.nationality
            )
            ResponseEntity.ok(ApiResponse(status = "success", data = response))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse(status = "error", message = "Author not found"))
        }
    }

    @DeleteMapping("/{id}")
    fun deleteAuthor(@PathVariable id: Long): ResponseEntity<ApiResponse<Void>> {
        return try {
            authorService.deleteAuthorById(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse(status = "success"))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(status = "error", message = "Failed to delete author"))
        }
    }

    @PostMapping("/search")
    fun searchAuthors(@RequestBody searchAuthorsRequest: SearchAuthorsRequest): ResponseEntity<ApiResponse<List<AuthorResponse>>> {
        val authors = authorService.searchAuthors(
            name = searchAuthorsRequest.name,
            nationality = searchAuthorsRequest.nationality,
            birthdate = searchAuthorsRequest.birthdate
        ).map { author ->
            AuthorResponse(
                id = author.id!!,
                name = author.name,
                birthdate = author.birthdate,
                nationality = author.nationality
            )
        }
        val response = ApiResponse(
            status = "success",
            data = authors
        )
        return ResponseEntity.ok(response)
    }
}