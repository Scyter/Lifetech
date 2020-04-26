package com.scyter.lifetech.db

import com.scyter.lifetech.data.FlowableProductsDataSource
import com.scyter.lifetech.data.ProductsStorage
import com.scyter.lifetech.db.entity.toEntities
import com.scyter.lifetech.db.entity.toEntity
import com.scyter.lifetech.db.entity.toProductDetails
import com.scyter.lifetech.db.entity.toProducts
import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DbDataSource(
    private val database: LifetechDatabase
) : FlowableProductsDataSource, ProductsStorage {

    override suspend fun getProducts(): Flow<List<Product>> {
        return database.productDao().getProducts().map {
            it.toProducts()
        }
    }

    override suspend fun getProductDetails(productId: String): Flow<ProductDetails> {
        return database.productDetailsDao().getProductDetails(productId).map {
            it.toProductDetails()
        }
    }

    override suspend fun storeProducts(products: List<Product>) {
        database.productDao().insertAll(products.toEntities())
    }

    override suspend fun storeProductDetails(product: ProductDetails, replace: Boolean) {
        if (replace) {
            database.productDetailsDao().insert(product.toEntity())
        } else {
            database.productDetailsDao().insertFromProduct(product.toEntity())
        }
    }
}