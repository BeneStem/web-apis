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

    fun find(search: String?, cheaperThan: Int?): Flux<Product> {
       TODO()
    }
}
