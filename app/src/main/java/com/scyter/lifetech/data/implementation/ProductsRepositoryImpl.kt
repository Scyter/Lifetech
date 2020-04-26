package com.scyter.lifetech.data.implementation

import com.scyter.lifetech.api.ApiDataSource
import com.scyter.lifetech.data.ProductDetailsResult
import com.scyter.lifetech.data.ProductsResult
import com.scyter.lifetech.db.DbDataSource
import com.scyter.lifetech.domain.RepositoryError
import com.scyter.lifetech.domain.ProductsRepository
import com.scyter.lifetech.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext


class ProductsRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val dbDataSource: DbDataSource
) : ProductsRepository {

    private val errorFlow: Flow<RepositoryError> = callbackFlow {
        awaitClose {

        }
    }

    override suspend fun updateProducts() {
        val productsResult = apiDataSource.getProducts()
        if (productsResult is ProductsResult.Success) {
            dbDataSource.storeProducts(productsResult.products)
        } else {
            // TODO send this error to errorFlow
        }
    }

    override suspend fun subscribeToProducts(): Flow<List<Product>> {
        return dbDataSource.getProducts()
    }

    override suspend fun updateProductDetails(productId: String) {
        val productDetailsResult = apiDataSource.getProductDetails(productId)
        if (productDetailsResult is ProductDetailsResult.Success) {
            dbDataSource.storeProduct(productDetailsResult.product)
        } else {
            // TODO send this error to errorFlow
        }
    }

    override suspend fun subscribeToProductDetails(productId: String): Flow<Product> {
        return dbDataSource.getProductDetails(productId)
    }

    override suspend fun subscribeToErrors(): Flow<RepositoryError> {
        return errorFlow
    }
}