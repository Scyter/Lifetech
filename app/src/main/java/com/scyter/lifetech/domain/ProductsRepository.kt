package com.scyter.lifetech.domain

import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun updateProducts()

    suspend fun subscribeToProducts(): Flow<List<Product>>

    suspend fun updateProductDetails(productId: String)

    suspend fun saveProductDetails(product: Product)

    suspend fun subscribeToProductDetails(productId: String): Flow<ProductDetails>

    suspend fun subscribeToErrors(): Flow<RepositoryError>
}

sealed class RepositoryError {
    data class ProductsError(val message: String) : RepositoryError()

    data class ProductDetailError(val message: String) : RepositoryError()

    data class InfrastructureError(val message: String) : RepositoryError()
}
