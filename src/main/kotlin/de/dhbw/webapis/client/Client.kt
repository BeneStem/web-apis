package de.dhbw.webapis.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Client

fun main(vararg args: String) {
  System.setProperty("server.port", "8081")
  runApplication<Client>(*args)
}
