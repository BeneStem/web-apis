package de.dhbw.webapis.server.web

import de.dhbw.webapis.server.repository.ProductRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable

@Controller
@RequestMapping("/products")
class ProductsHtmlController(val productRepository: ProductRepository) {

  @GetMapping
  fun getProducts(model: Model): String {
    model.addAttribute("products", ReactiveDataDriverContextVariable(productRepository.findAll()))
    return "productsOverview"
  }
}
