package de.dhbw.webapis.client.web

import de.dhbw.webapis.client.repository.ProductClientRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable

@Controller
@RequestMapping("/products")
class ProductsClientHtmlController(val productClientRepository: ProductClientRepository) {

  @GetMapping
  fun getProducts(model: Model): String {
    model.addAttribute("products", ReactiveDataDriverContextVariable(productClientRepository.findAll()))
    return "productsOverview"
  }
}
