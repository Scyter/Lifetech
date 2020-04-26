package com.scyter.lifetech.data

import com.scyter.lifetech.domain.model.Product

interface ProductsDataSource {

    suspend fun getProducts(): ProductsResult

    suspend fun getProductDetails(productId: String): ProductDetailsResult
}

sealed class ProductsResult {

    data class Success(val products: List<Product>) : ProductsResult()

    data class Error(val message: String) : ProductsResult()
}

sealed class ProductDetailsResult {

    data class Success(val product: Product) : ProductDetailsResult()

    data class Error(val message: String) : ProductDetailsResult()
}