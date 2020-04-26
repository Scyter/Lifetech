package com.scyter.lifetech.data

import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.domain.model.ProductDetails
import kotlinx.coroutines.flow.Flow


interface FlowableProductsDataSource {

    suspend fun getProducts(): Flow<List<Product>>

    suspend fun getProductDetails(productId: String): Flow<ProductDetails>

}