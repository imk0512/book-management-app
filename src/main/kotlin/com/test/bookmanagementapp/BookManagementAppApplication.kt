package com.test.bookmanagementapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookManagementAppApplication

fun main(args: Array<String>) {
    runApplication<BookManagementAppApplication>(*args)
}
