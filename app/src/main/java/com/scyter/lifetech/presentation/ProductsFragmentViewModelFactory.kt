package com.scyter.lifetech.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scyter.lifetech.domain.ProductDetailsRepository
import com.scyter.lifetech.domain.usecase.GetProductsUseCase


class ProductsFragmentViewModelFactory(
    private val getProductsUseCase: GetProductsUseCase,
    private val productDetailsRepository: ProductDetailsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsFragmentViewModel(
            getProductsUseCase,
            productDetailsRepository
        ) as T
    }
}