package com.scyter.lifetech.api.response

import com.google.gson.annotations.SerializedName
import com.scyter.lifetech.domain.model.Product

data class ProductsResponse(
    @SerializedName("products")
    val products: List<Product>
)