package com.scyter.lifetech.domain

import androidx.lifecycle.MutableLiveData


class ProductDetailsRepository {
    val selectedProductId: MutableLiveData<String?> = MutableLiveData()
}