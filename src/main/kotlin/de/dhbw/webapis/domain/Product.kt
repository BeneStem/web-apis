package de.dhbw.webapis.domain

import org.hibernate.validator.constraints.Length
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Null

@Document("products")
data class Product(
  @field:Null
  @Id val id: String?,
  @field:NotBlank
  @field:Length(min = 5, max = 10)
  val name: String,
  val vegan: Boolean
) {

  companion object {
    const val APPLICATION_PRODUCT_JSON_VALUE = "application/de.dhbw.product+json"
  }
}
