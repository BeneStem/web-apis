package de.dhbw.webapis.client.service

import de.dhbw.webapis.client.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun find(search: String?, cheaperThan: Int?) = productRepository.find(search, cheaperThan)
}
