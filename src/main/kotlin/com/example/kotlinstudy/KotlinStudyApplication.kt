package com.example.kotlinstudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.jvm.optionals.toList

@SpringBootApplication
class KotlinStudyApplication

fun main(args: Array<String>) {
    runApplication<KotlinStudyApplication>(*args)
}

@RestController
class MessageController(val messageService: MessageService) {
    @GetMapping("/")
    fun index(): List<Message> = messageService.findMessage()
    @GetMapping("/{id}")
    fun index(@PathVariable id: String): List<Message> = messageService.findMessageById(id)
    @PostMapping("/")
    fun post(@RequestBody message: Message){
        messageService.save(message)
    }
}

@Table("MESSAGES")
data class Message(@Id var id: String?, val text: String)

@Service
class MessageService(val db: MessageRepository){
    fun findMessage(): List<Message> = db.findAll().toList()

    fun findMessageById(id: String): List<Message> = db.findById(id).toList()

    fun save(message: Message) {
        db.save(message)
    }
    fun <T: Any> Optional<out T>.toList(): List<T> = if (isPresent) listOf(get()) else emptyList()
}

interface MessageRepository: CrudRepository<Message, String>

