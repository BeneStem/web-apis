package de.dhbw.webapis.server.web

import de.dhbw.webapis.common.domain.Product
import de.dhbw.webapis.common.domain.Product.Companion.APPLICATION_PRODUCT_JSON_VALUE
import de.dhbw.webapis.server.repository.ProductServerRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductsServerRestController(val productServerRepository: ProductServerRepository) {

  @GetMapping(produces = [APPLICATION_PRODUCT_JSON_VALUE])
  fun getProducts(@RequestParam(required = false) vegan: Boolean?, @RequestParam(required = false) contains: String?) =
    productServerRepository.find(vegan, contains)

  @PostMapping(
    consumes = [APPLICATION_PRODUCT_JSON_VALUE],
    produces = [APPLICATION_PRODUCT_JSON_VALUE])
  fun addProduct(@RequestBody @Valid product: Mono<Product>) = productServerRepository.save(product)
}
