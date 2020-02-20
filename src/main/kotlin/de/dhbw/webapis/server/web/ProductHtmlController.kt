package de.dhbw.webapis.server.web

import org.springframework.http.MediaType.TEXT_HTML_VALUE
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/products")
class ProductHtmlController {

    @GetMapping(produces = [TEXT_HTML_VALUE])
    fun getProducts() = "products"
}
