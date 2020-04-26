package com.scyter.lifetech.data.implementation

import com.scyter.lifetech.api.ApiDataSource
import com.scyter.lifetech.data.ProductDetailsResult
import com.scyter.lifetech.data.ProductsResult
import com.scyter.lifetech.db.DbDataSource
import com.scyter.lifetech.domain.ProductsRepository
import com.scyter.lifetech.domain.RepositoryError
import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class ProductsRepositoryImpl(
    private val apiDataSource: ApiDataSource,
    private val dbDataSource: DbDataSource
) : ProductsRepository {

    //    errorFlow not implemented
    private val errorFlow: Flow<RepositoryError> = callbackFlow {
        // not implemented
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
            dbDataSource.storeProductDetails(productDetailsResult.product, true)
        } else {
            // TODO send this error to errorFlow
        }
    }

    override suspend fun saveProductDetails(product: Product) {
        dbDataSource.storeProductDetails(
            ProductDetails(
                product.productId,
                product.name,
                product.price,
                product.image,
                ""
            ), false
        )
    }

    override suspend fun subscribeToProductDetails(productId: String): Flow<ProductDetails> {
        return dbDataSource.getProductDetails(productId)
    }

    override suspend fun subscribeToErrors(): Flow<RepositoryError> {
        return errorFlow
    }
}