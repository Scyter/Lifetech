package com.scyter.lifetech.api

import com.scyter.lifetech.api.response.ProductDetailResponse
import com.scyter.lifetech.api.response.ProductsResponse
import com.scyter.lifetech.domain.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("cart/list")
    fun getProducts(): Call<ProductsResponse>

    @GET("cart/{product_id}/detail")
    fun getProductDetail(
        @Path("product_id") productId: String
    ): Call<Product>
}