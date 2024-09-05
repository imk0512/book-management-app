package com.test.bookmanagementapp.controller

import com.test.bookmanagementapp.dto.common.ApiResponse
import com.test.bookmanagementapp.dto.request.author.CreateAuthorRequest
import com.test.bookmanagementapp.dto.request.author.SearchAuthorsRequest
import com.test.bookmanagementapp.dto.request.author.UpdateAuthorRequest
import com.test.bookmanagementapp.dto.response.author.AuthorResponse
import com.test.bookmanagementapp.dto.response.author.SearchAuthorsResponse
import com.test.bookmanagementapp.service.AuthorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Author API", description = "API for managing authors")
@RestController
@RequestMapping("/api/authors")
class AuthorController(private val authorService: AuthorService) {

    @Operation(summary = "全ての著者を取得", description = "全ての著者をデータベースから取得します。削除された著者は含まれません。")
    @GetMapping
    fun getAllAuthors(): ResponseEntity<ApiResponse<List<AuthorResponse>>> {
        val authors = authorService.getAllAuthors().map { author ->
            AuthorResponse(
                id = author.id!!,
                name = author.name,
                birthdate = author.birthdate
            )
        }
        val response = ApiResponse(
            status = "success",
            data = authors
        )
        return ResponseEntity.ok(response)
    }

    @Operation(summary = "著者をIDで取得", description = "指定されたIDの著者情報を取得します。著者が存在しない場合は404エラーを返します。")
    @GetMapping("/{id}")
    fun getAuthorById(@PathVariable id: Long): ResponseEntity<ApiResponse<AuthorResponse>> {
        val author = authorService.getAuthorById(id)
        return if (author != null) {
            val response = AuthorResponse(
                id = author.id!!,
                name = author.name,
                birthdate = author.birthdate
            )
            ResponseEntity.ok(ApiResponse(status = "success", data = response))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse(status = "error", message = "Author not found"))
        }
    }

    @Operation(summary = "著者をIDで取得", description = "指定されたIDの著者情報を取得します。著者が存在しない場合は404エラーを返します。")
    @PostMapping
    fun createAuthor(@RequestBody createAuthorRequest: CreateAuthorRequest): ResponseEntity<ApiResponse<AuthorResponse>> {
        val author = authorService.createAuthor(
            name = createAuthorRequest.name,
            birthdate = createAuthorRequest.birthdate
        )
        val response = AuthorResponse(
            id = author.id!!,
            name = author.name,
            birthdate = author.birthdate
        )
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse(status = "success", data = response))
    }

    @Operation(summary = "著者情報を更新", description = "指定されたIDの著者情報を更新します。存在しないIDの場合は404エラーを返します。")
    @PutMapping("/{id}")
    fun updateAuthor(@PathVariable id: Long, @RequestBody updateAuthorRequest: UpdateAuthorRequest): ResponseEntity<ApiResponse<AuthorResponse>> {
        val updatedAuthor = authorService.updateAuthor(
            id = id,
            name = updateAuthorRequest.name,
            birthdate = updateAuthorRequest.birthdate
        )
        return if (updatedAuthor != null) {
            val response = AuthorResponse(
                id = updatedAuthor.id!!,
                name = updatedAuthor.name,
                birthdate = updatedAuthor.birthdate
            )
            ResponseEntity.ok(ApiResponse(status = "success", data = response))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse(status = "error", message = "Author not found"))
        }
    }

    @Operation(summary = "著者を削除", description = "指定されたIDの著者を論理削除します。関連する書籍も論理削除されます。")
    @DeleteMapping("/{id}")
    fun deleteAuthor(@PathVariable id: Long): ResponseEntity<ApiResponse<Void>> {
        return try {
            authorService.deleteAuthorById(id)
            ResponseEntity.ok(ApiResponse(status = "success", data = null))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse(status = "error", message = "Failed to delete author"))
        }
    }

    @Operation(summary = "著者を検索", description = "指定された名前や生年月日で著者を検索します。名前は部分一致で検索されます。削除された著者は含まれません。")
    @PostMapping("/search")
    fun searchAuthors(@RequestBody searchAuthorsRequest: SearchAuthorsRequest): ResponseEntity<SearchAuthorsResponse> {
        val authors = authorService.searchAuthors(searchAuthorsRequest.name, searchAuthorsRequest.birthdate)

        return if (authors.isEmpty()) {
            ResponseEntity.ok(SearchAuthorsResponse(authors = emptyList()))
        } else {
            ResponseEntity.ok(SearchAuthorsResponse(authors = authors.map { AuthorResponse(it.id!!, it.name, it.birthdate) }))
        }
    }
}
