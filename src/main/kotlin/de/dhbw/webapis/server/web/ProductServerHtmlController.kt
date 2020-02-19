package de.dhbw.webapis.server.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/p")
class ProductsServerHtmlController {

    @GetMapping
    fun getProducts() = "products"
}
