package com.scyter.lifetech.data

import com.scyter.lifetech.domain.model.Product


interface ProductsStorage {
    suspend fun storeProducts(products: List<Product>)

    suspend fun storeProduct(product: Product)
}