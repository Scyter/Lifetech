package com.scyter.lifetech.presentation

import androidx.lifecycle.MutableLiveData
import com.scyter.lifetech.domain.model.Product


open class ProductViewModel {
    private val name = MutableLiveData<String>()
    private val price = MutableLiveData<String>()
    private val image = MutableLiveData<String>()

    open fun bind(product: Product) {
        name.value = product.name
        price.value = product.price.toString()
        image.value = product.image
    }

    fun getProductName() = name
    fun getProductPrice() = price
    fun getProductImage() = image
}