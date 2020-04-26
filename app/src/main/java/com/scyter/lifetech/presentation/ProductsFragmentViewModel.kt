package com.scyter.lifetech.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scyter.lifetech.domain.ProductDetailsRepository
import com.scyter.lifetech.domain.model.Product
import com.scyter.lifetech.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsFragmentViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val productDetailsRepository: ProductDetailsRepository
) : ViewModel() {

    private val _products: MutableLiveData<List<Product>> = MutableLiveData(emptyList())
    val products: LiveData<List<Product>> = _products

    init {
        viewModelScope.launch {
            loadProducts()
        }

        viewModelScope.launch {
            updateProducts()
        }
    }

    fun onProductSelected(productId: String) {
        val product = _products.value?.firstOrNull { it.productId == productId } ?: return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getProductsUseCase.updateProductDetails(product)
            }
        }
        productDetailsRepository.selectedProductId.value = productId
    }

    private suspend fun loadProducts() {
        withContext(Dispatchers.IO) {
            getProductsUseCase.getProducts().collect {
                withContext(Dispatchers.Main) {
                    _products.value = it
                }
            }
        }
    }

    private suspend fun updateProducts() {
        withContext(Dispatchers.IO) {
            getProductsUseCase.updateProducts()
        }
    }
}
