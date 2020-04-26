package com.scyter.lifetech.data

import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.domain.model.ProductDetails


interface ProductsStorage {
    suspend fun storeProducts(products: List<Product>)

    suspend fun storeProductDetails(product: ProductDetails, replace: Boolean)
}