package de.dhbw.webapis.client.repository

import de.dhbw.webapis.common.domain.Product
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux

@Repository
class ProductClientRepository(webClientBuilder: WebClient.Builder) {

  val webClient: WebClient = webClientBuilder.baseUrl("http://localhost:8080").build()

  fun findAll() = webClient.get()
    .uri("/products")
    .retrieve()
    .bodyToFlux<Product>()
}
