package com.scyter.lifetech.api

import com.scyter.lifetech.data.ProductDetailsResult
import com.scyter.lifetech.data.ProductsDataSource
import com.scyter.lifetech.data.ProductsResult
import retrofit2.awaitResponse

class ApiDataSource(private val api: ProductApi) : ProductsDataSource {

    override suspend fun getProducts(): ProductsResult {
        return try {
            val response = api.getProducts().awaitResponse()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ProductsResult.Success(body.products)
                } else {
                    ProductsResult.Error("no result")
                }
            } else {
                ProductsResult.Error("response failed")
            }
        } catch (throwable: Throwable) {
            ProductsResult.Error(throwable.message ?: "")
        }
    }

    override suspend fun getProductDetails(productId: String): ProductDetailsResult {
        return try {
            val response = api.getProductDetail(productId).awaitResponse()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ProductDetailsResult.Success(body)
                } else {
                    ProductDetailsResult.Error("no result")
                }
            } else {
                ProductDetailsResult.Error("response failed")
            }
        } catch (throwable: Throwable) {
            ProductDetailsResult.Error(throwable.message ?: "")
        }
    }
}