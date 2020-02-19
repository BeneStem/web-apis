package de.dhbw.webapis.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Server

fun main() {
    System.setProperty("server.port", "8081")
    runApplication<Server>()
}
