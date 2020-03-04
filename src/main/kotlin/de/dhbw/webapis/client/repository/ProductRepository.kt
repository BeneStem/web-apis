package de.dhbw.webapis.client.repository

import de.dhbw.webapis.common.domain.Product
import org.springframework.http.MediaType.TEXT_EVENT_STREAM
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux

@Repository
class ProductRepository {

    private val webClient = WebClient.create("http://localhost:8081")

    fun find(search: String?, cheaperThan: Int?): Flux<Product> {
        val uri = UriComponentsBuilder.fromPath("/products")
        if (search != null) {
            uri.queryParam("search", search)
        }
        if (cheaperThan != null) {
            uri.queryParam("cheaperThan", cheaperThan)
        }
        return webClient.get()
                .uri(uri.build().toUriString())
                .accept(TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux()
    }
}
