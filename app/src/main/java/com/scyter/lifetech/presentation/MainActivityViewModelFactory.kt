package com.scyter.lifetech.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MainActivityViewModelFactory () : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel() as T
    }
}