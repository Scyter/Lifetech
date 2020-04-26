package com.scyter.lifetech.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.scyter.lifetech.domain.ProductDetailsRepository
import com.scyter.lifetech.domain.usecase.GetProductDetailsUseCase


class ProductDetailsFragmentViewModelFactory(
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val productDetailsRepository: ProductDetailsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductDetailsFragmentViewModel(
            getProductDetailsUseCase,
            productDetailsRepository
        ) as T
    }
}