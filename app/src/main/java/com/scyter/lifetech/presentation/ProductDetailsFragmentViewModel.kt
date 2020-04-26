package com.scyter.lifetech.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scyter.lifetech.domain.ProductDetailsRepository
import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.domain.usecase.GetProductDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductDetailsFragmentViewModel(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val productDetailsRepository: ProductDetailsRepository
) : ViewModel() {

    private var product: Product? = null

    private val _name = MutableLiveData<String>()
    private val _price = MutableLiveData<String>()
    private val _image = MutableLiveData<String>()
    private val _description = MutableLiveData<String>()

    init {
        val productId = productDetailsRepository.selectedProductId.value
        if (productId == null) {
            // show error
        } else {
            viewModelScope.launch {
                loadProducts(productId)
            }
            viewModelScope.launch {
                updateProductDetails(productId)
            }
        }
    }

    private suspend fun loadProducts(productId: String) {
        withContext(Dispatchers.IO) {
            getProductDetailsUseCase.getProductDetail(productId)
                .collect {
                    withContext(Dispatchers.Main) {
                        product = it
                        updateScreen()
                    }
                }
        }
    }

    private suspend fun updateProductDetails(productId: String) {
        withContext(Dispatchers.IO) {
            getProductDetailsUseCase.updateProductDetails(productId)
        }
    }

    private fun updateScreen() {
        _name.value = product?.name
        _price.value = product?.price.toString()
        _image.value = product?.image
        _description.value = product?.description
    }

    fun getProductName() = _name
    fun getProductPrice() = _price
    fun getProductImage() = _image
    fun getProductDescription() = _description
}