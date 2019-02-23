package de.dhbw.webapis.web

import de.dhbw.webapis.domain.Product
import de.dhbw.webapis.domain.Product.Companion.APPLICATION_PRODUCT_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductsController {

  val products = ArrayList<Product>()

  @GetMapping(produces = [APPLICATION_PRODUCT_JSON_VALUE])
  fun getProducts() = products.toFlux()

  @PostMapping(
    consumes = [APPLICATION_PRODUCT_JSON_VALUE],
    produces = [APPLICATION_PRODUCT_JSON_VALUE])
  fun addProduct(@RequestBody @Valid product: Product): Mono<Product> {
    products.add(product)
    return Mono.just(product)
  }
}
