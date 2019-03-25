package de.dhbw.webapis.server.repository

import de.dhbw.webapis.common.domain.Product
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findAll
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class ProductRepository(val reactiveMongoTemplate: ReactiveMongoTemplate) {

  fun findAll() = reactiveMongoTemplate.findAll<Product>()

  fun find(vegan: Boolean?, contains: String?): Flux<Product> {
    val query = Query()
    if (vegan != null) {
      query.addCriteria(Criteria.where(Product::vegan.name).`is`(vegan))
    }
    if (contains != null) {
      query.addCriteria(Criteria.where(Product::name.name).regex(".*$contains.*"))
    }
    return reactiveMongoTemplate.find(query)
  }

  fun save(product: Mono<Product>) = reactiveMongoTemplate.save(product)
}
