package com.scyter.lifetech.domain.usecase

import com.scyter.lifetech.domain.ProductsRepository
import com.scyter.lifetech.domain.RepositoryError
import com.scyter.lifetech.domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter


interface GetProductDetailsUseCase {
    suspend fun getProductDetail(productId: String): Flow<ProductDetails>

    suspend fun updateProductDetails(productId: String)

    suspend fun subscribeToProductErrors(): Flow<RepositoryError>
}

class GetProductDetailsUseCaseImpl(
    private val productsRepository: ProductsRepository
) : GetProductDetailsUseCase {

    override suspend fun getProductDetail(productId: String): Flow<ProductDetails> {
        return productsRepository.subscribeToProductDetails(productId)
    }

    override suspend fun updateProductDetails(productId: String) {
        productsRepository.updateProductDetails(productId)
    }

    override suspend fun subscribeToProductErrors(): Flow<RepositoryError> {
        return productsRepository.subscribeToErrors()
            .filter {
                it is RepositoryError.ProductDetailError || it is RepositoryError.InfrastructureError
            }
    }
}