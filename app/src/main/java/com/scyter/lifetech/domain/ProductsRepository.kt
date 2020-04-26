package com.scyter.lifetech.domain

import com.scyter.lifetech.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun updateProducts()

    suspend fun subscribeToProducts(): Flow<List<Product>>

    suspend fun updateProductDetails(productId: String)

    suspend fun subscribeToProductDetails(productId: String): Flow<Product>

    suspend fun subscribeToErrors(): Flow<RepositoryError>
}

sealed class RepositoryError {
    data class ProductsError(val message: String) : RepositoryError()

    data class ProductDetailError(val message: String) : RepositoryError()

    data class InfrastructureError(val message: String) : RepositoryError()
}
