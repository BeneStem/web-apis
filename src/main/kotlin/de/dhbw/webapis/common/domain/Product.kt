package de.dhbw.webapis.common.domain

import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Null

@Document("products")
data class Product(
        @field:Null
        @Id val id: String?,
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
