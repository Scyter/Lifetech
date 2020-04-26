package com.scyter.lifetech.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id")
    val productId: String,
    val name: String,
    val price: Int,
    val image: String,
    val description: String = ""
)