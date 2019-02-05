package de.dhbw.webapis.domain

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank

data class Product(

  @field:NotBlank
  @field:Length(min = 5, max = 10)
  val name: String,
  val vegan: Boolean
) {

  companion object {
    const val APPLICATION_PRODUCT_JSON_VALUE = "application/de.dbhw.product+json"
  }
}
