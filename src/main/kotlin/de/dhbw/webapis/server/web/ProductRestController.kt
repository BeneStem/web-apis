package de.dhbw.webapis.server.web

import de.dhbw.webapis.common.domain.Product
import de.dhbw.webapis.common.domain.Product.Companion.APPLICATION_PRODUCT_JSON_VALUE
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductRestController {

    val products = mutableListOf(Product("Schokolade", 6))

    @GetMapping(produces = [APPLICATION_PRODUCT_JSON_VALUE])
    fun getProducts(@RequestParam(required = false) search: String?,
                    @RequestParam(required = false) cheaperThan: Int?) =
            if (search != null && cheaperThan == null) {
                products.distinct().filter {
                    it.name.contains(search, true)
                }.toFlux()
            } else if (search == null && cheaperThan != null) {
                products.distinct().filter {
                    it.price < cheaperThan
                }.toFlux()
            } else if (search != null && cheaperThan != null) {
                products.distinct().filter {
                    it.name.contains(search, true) && it.price < cheaperThan
                }.toFlux()
            } else {
                products.distinct().toFlux()
            }

    @PostMapping(consumes = [APPLICATION_PRODUCT_JSON_VALUE],
            produces = [APPLICATION_PRODUCT_JSON_VALUE])
    @ResponseStatus(CREATED)
    fun addProduct(@RequestBody @Valid product: Mono<Product>) =
            product.doOnNext {
                products.add(it)
            }

    @PutMapping(path = ["/{productName}"],
            consumes = [APPLICATION_PRODUCT_JSON_VALUE],
            produces = [APPLICATION_PRODUCT_JSON_VALUE])
    fun updateProduct(@PathVariable productName: String, @RequestBody @Valid body: Mono<Product>) =
            // TODO add validation
            body.doOnNext { product ->
                products.replaceAll {
                    if (it.name == productName) product else it
                }
            }

    @DeleteMapping("/{productName}")
    @ResponseStatus(NO_CONTENT)
    fun deleteProduct(@PathVariable productName: String) {
        products.removeAll { it.name == productName }
    }
}
