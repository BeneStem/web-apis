package de.dhbw.webapis.common.domain

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank

data class Product(
        @field:NotBlank
        @field:Length(min = 3, max = 15)
        val name: String,
        @field:Range(min = 1, max = 10)
        val price: Int
) {
        companion object {
                const val APPLICATION_PRODUCT_JSON_VALUE = "application/de.dhbw.product+json"
        }
}
