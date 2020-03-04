package de.dhbw.webapis.server.web

import de.dhbw.webapis.common.domain.Product
import de.dhbw.webapis.common.domain.Product.Companion.APPLICATION_PRODUCT_JSON_VALUE
import de.dhbw.webapis.server.service.ProductService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
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
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductRestController(val productService: ProductService) {

    @GetMapping(produces = [APPLICATION_PRODUCT_JSON_VALUE, TEXT_EVENT_STREAM_VALUE])
    fun find(@RequestParam(required = false) search: String?,
             @RequestParam(required = false) cheaperThan: Int?) =
            productService.find(search, cheaperThan)

    @PostMapping(consumes = [APPLICATION_PRODUCT_JSON_VALUE],
            produces = [APPLICATION_PRODUCT_JSON_VALUE])
    @ResponseStatus(CREATED)
    fun save(@RequestBody @Valid product: Mono<Product>) =
            productService.save(product)

    @PutMapping(path = ["/{productId}"],
            consumes = [APPLICATION_PRODUCT_JSON_VALUE],
            produces = [APPLICATION_PRODUCT_JSON_VALUE])
    fun update(@PathVariable productId: String,
               @RequestBody @Valid product: Mono<Product>) =
            productService.update(productId, product)

    @DeleteMapping("/{productId}")
    @ResponseStatus(NO_CONTENT)
    fun remove(@PathVariable productId: String) =
            productService.remove(productId)
}
