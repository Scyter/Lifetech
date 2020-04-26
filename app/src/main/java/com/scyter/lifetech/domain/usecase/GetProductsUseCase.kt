package com.scyter.lifetech.domain.usecase

import com.scyter.lifetech.domain.ProductsRepository
import com.scyter.lifetech.domain.RepositoryError
import com.scyter.lifetech.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

interface GetProductsUseCase {
    suspend fun getProducts(): Flow<List<Product>>

    suspend fun updateProducts()

    suspend fun subscribeToProductErrors(): Flow<RepositoryError>
}

class GetProductsUseCaseImpl(
    private val productsRepository: ProductsRepository
) : GetProductsUseCase {

    override suspend fun getProducts(): Flow<List<Product>> {
        return productsRepository.subscribeToProducts()
    }

    override suspend fun subscribeToProductErrors(): Flow<RepositoryError> {
        return productsRepository.subscribeToErrors()
            .filter {
                it is RepositoryError.ProductsError || it is RepositoryError.InfrastructureError
            }
    }

    override suspend fun updateProducts() {
        productsRepository.updateProducts()
    }
}