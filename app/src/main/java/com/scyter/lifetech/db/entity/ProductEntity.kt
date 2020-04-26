package com.scyter.lifetech.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.scyter.lifetech.domain.model.Product


@Entity
data class ProductEntity(
    @PrimaryKey
    val productId: String,

    val name: String,

    val price: Int,

    val image: String,

    val description: String? = ""
)

fun ProductEntity.toProduct(): Product {
    return Product(productId, name, price, image, description ?: "")
}

fun List<ProductEntity>.toProducts(): List<Product> {
    return this.map { it.toProduct() }
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(productId, name, price, image, description)
}

fun List<Product>.toEntities(): List<ProductEntity> {
    return this.map { it.toEntity() }
}
