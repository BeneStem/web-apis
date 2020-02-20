package de.dhbw.webapis.server.service

import de.dhbw.webapis.common.domain.Product
import de.dhbw.webapis.server.repository.ProductRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProductService(val productRepository: ProductRepository) {

    fun find(search: String?, cheaperThan: Int?) = productRepository.find(search, cheaperThan)

    fun save(product: Mono<Product>) = productRepository.save(product)

    fun update(productId: String, product: Mono<Product>) = productRepository.update(productId, product)

    fun remove(productId: String) = productRepository.remove(productId)
}
