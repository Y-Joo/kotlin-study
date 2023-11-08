package com.example.kotlinstudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinStudyApplication

fun main(args: Array<String>) {
    runApplication<KotlinStudyApplication>(*args)
}

@RestController
class MessageController {
    @GetMapping("/")
    fun index() = listOf(
        Message("test1", "test1"),
        Message("test2", "test2"),
        Message("test3", "test3"),
    )
}

data class Message(val id: String?, val text: String)

}

