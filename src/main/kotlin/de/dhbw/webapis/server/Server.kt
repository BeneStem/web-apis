package de.dhbw.webapis.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Server

fun main(vararg args: String) {
  System.setProperty("server.port", "8080")
  runApplication<Server>(*args)
}
