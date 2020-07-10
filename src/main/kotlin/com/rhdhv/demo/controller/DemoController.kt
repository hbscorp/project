package com.rhdhv.demo.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class DemoController {

    private val clientList = mutableListOf(ClientDto(1, "John"), ClientDto(2, "Mary"))

    @GetMapping("/clients")
    fun getAll(): List<ClientDto> {
        return clientList
    }

    @GetMapping("/clients/{id}")
    fun getClient(@PathVariable id: Int): List<ClientDto> {
        return clientList.filter { it.id == id }
                .ifEmpty { throw ClientNotFound(id) }
    }

    @PostMapping("/clients")
    fun storeClient(@RequestBody client: CreateClientDto?): ResponseEntity<ClientDto> {
        if (client == null)
            return ResponseEntity.notFound()
                    .build()

        val newClient = ClientDto(clientList.size + 1, client.name)
        clientList.add(newClient);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newClient);
    }

}

data class ClientDto(
        val id: Int,
        val name: String
)

data class CreateClientDto(
        val name: String
)

@ResponseStatus(HttpStatus.NOT_FOUND)
class ClientNotFound(id: Int): Exception("Client with id $id was not found!")
