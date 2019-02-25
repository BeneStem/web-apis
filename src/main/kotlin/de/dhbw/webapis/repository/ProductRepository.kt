package de.dhbw.webapis.repository

import de.dhbw.webapis.domain.Product
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.findAll
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class ProductRepository(val reactiveMongoTemplate: ReactiveMongoTemplate) {

  fun findAll() = reactiveMongoTemplate.findAll<Product>()

  fun save(product: Mono<Product>) = reactiveMongoTemplate.save(product)
}
