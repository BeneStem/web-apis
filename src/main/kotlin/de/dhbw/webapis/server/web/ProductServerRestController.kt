package de.dhbw.webapis.server.web

import de.dhbw.webapis.common.domain.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

@RestController
@RequestMapping("/products")
class ProductsServerRestController {

    val products = mutableListOf(Product("Schokolade", 6))

    @GetMapping
    fun getProducts() = products.toFlux()

    @PostMapping
    fun addProduct(@RequestBody product: Mono<Product>) = product.doOnNext {
        products.add(it)
    }
}
