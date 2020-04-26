package com.scyter.lifetech.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.scyter.lifetech.domain.model.ProductDetails


@Entity
data class ProductDetailsEntity(
    @PrimaryKey
    val productId: String,

    val name: String,

    val price: Int,

    val image: String,

    val description: String = ""
)

fun ProductDetailsEntity.toProductDetails(): ProductDetails {
    return ProductDetails(productId, name, price, image, description)
}

fun ProductDetails.toEntity(): ProductDetailsEntity {
    return ProductDetailsEntity(productId, name, price, image, description ?: "")
}