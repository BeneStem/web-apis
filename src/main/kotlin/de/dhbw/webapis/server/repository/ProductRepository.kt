package de.dhbw.webapis.server.repository

import de.dhbw.webapis.common.domain.Product
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update.update
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class ProductRepository(val reactiveMongoTemplate: ReactiveMongoTemplate) {

    fun find(search: String?, cheaperThan: Int?): Flux<Product> {
        val query = Query()
        if (search != null) {
            query.addCriteria(where(Product::name.name).regex(".*$search.*"))
        }
        if (cheaperThan != null) {
            query.addCriteria(where(Product::price.name).lt(cheaperThan))
        }
        return reactiveMongoTemplate.find(query)
    }

    fun save(product: Mono<Product>) =
            reactiveMongoTemplate.save(product)


    fun update(productId: String, product: Mono<Product>) =
            product.flatMap {
                reactiveMongoTemplate.updateFirst(query(where(Product::id.name).`is`(productId)),
                        update(Product::name.name, it.name).set(Product::price.name, it.price),
                        Product::class.java)
            }

    fun remove(productId: String) =
            reactiveMongoTemplate.remove(query(where(Product::id.name).`is`(productId)),
                    Product::class.java)
}
