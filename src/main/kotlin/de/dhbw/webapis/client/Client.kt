package de.dhbw.webapis.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Client

fun main() {
    System.setProperty("server.port", "8082")
    runApplication<Client>()
}
