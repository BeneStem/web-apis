package de.dhbw.webapis.server.web

import de.dhbw.webapis.server.service.ProductService
import org.springframework.http.MediaType.TEXT_HTML_VALUE
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable

@Controller
@RequestMapping("/products")
class ProductHtmlController(val productService: ProductService) {

    @GetMapping(produces = [TEXT_HTML_VALUE])
    fun find(@RequestParam(required = false) search: String?,
             @RequestParam(required = false) cheaperThan: Int?,
             model: Model): String {
        model.addAttribute("products", ReactiveDataDriverContextVariable(productService.find(search, cheaperThan)))
        return "products"
    }
}
