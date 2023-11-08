package com.example.kotlinstudy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.util.*

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

data class Message(val id: String?, val text: String)

@Service
class MessageService(val db: JdbcTemplate){
    fun findMessage(): List<Message> = db.query("select * from messages") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun findMessageById(id: String): List<Message> = db.query("select * from messages where id = '$id'") { response, _ ->
        Message(response.getString("id"), response.getString("text"))
    }

    fun save(message: Message){
        val id = message.id ?: UUID.randomUUID().toString()
        db.update("insert into messages values (?, ?)",
        id, message.text
        )
    }
}

