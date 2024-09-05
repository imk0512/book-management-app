package com.test.bookmanagementapp.exception

import com.test.bookmanagementapp.dto.common.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import jakarta.validation.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@ControllerAdvice
@RestController
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<ApiResponse<Void>> {
        logger.warn("Invalid parameter: ${ex.name} with value ${ex.value}. Expected type: ${ex.requiredType?.simpleName}", ex)
        val response = ApiResponse<Void>(
            status = "error",
            message = "Invalid parameter type: ${ex.name}. Expected type: ${ex.requiredType?.simpleName}"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ApiResponse<Void>> {
        val response = ApiResponse<Void>(
            status = "error",
            message = "Invalid request body: ${ex.message}"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<List<String>>> {
        val errors = ex.bindingResult.fieldErrors.map { "${it.field}: ${it.defaultMessage}" }
        val response = ApiResponse(
            status = "error",
            message = "Validation failed",
            data = errors
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }


    @ExceptionHandler(ResourceNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ApiResponse<Void>> {
        logger.warn("Resource not found: ${ex.message}", ex)
        val response = ApiResponse<Void>(
            status = "error",
            message = ex.message
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ApiResponse<Void>> {
        logger.warn("Illegal argument: ${ex.message}", ex)
        val response = ApiResponse<Void>(
            status = "error",
            message = ex.message ?: "Invalid request"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }


    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<ApiResponse<Void>> {
        logger.warn("Constraint violation: ${ex.message}", ex)
        val errorMessage = ex.constraintViolations.joinToString(", ") { "${it.propertyPath}: ${it.message}" }
        val response = ApiResponse<Void>(
            status = "error",
            message = "Validation error: $errorMessage"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException): ResponseEntity<ApiResponse<Void>> {
        logger.warn("Data integrity violation: ${ex.message}", ex)
        val response = ApiResponse<Void>(
            status = "error",
            message = "Data integrity violation: Please check your input"
        )
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(ex: Exception): ResponseEntity<ApiResponse<Void>> {
        logger.error("An unexpected error occurred: ${ex.message}", ex)
        val response = ApiResponse<Void>(
            status = "error",
            message = "An unexpected error occurred"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}
