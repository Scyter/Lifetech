package com.scyter.lifetech

import androidx.room.Room
import com.scyter.lifetech.api.ApiDataSource
import com.scyter.lifetech.api.ProductApi
import com.scyter.lifetech.api.RestService
import com.scyter.lifetech.data.implementation.ProductsRepositoryImpl
import com.scyter.lifetech.db.DbDataSource
import com.scyter.lifetech.db.LifetechDatabase
import com.scyter.lifetech.domain.ProductDetailsRepository
import com.scyter.lifetech.domain.ProductsRepository
import com.scyter.lifetech.domain.usecase.GetProductDetailsUseCase
import com.scyter.lifetech.domain.usecase.GetProductDetailsUseCaseImpl
import com.scyter.lifetech.domain.usecase.GetProductsUseCase
import com.scyter.lifetech.domain.usecase.GetProductsUseCaseImpl
import com.scyter.lifetech.presentation.MainActivityViewModelFactory
import com.scyter.lifetech.presentation.ProductDetailsFragmentViewModelFactory
import com.scyter.lifetech.presentation.ProductsFragmentViewModelFactory

// Here planed to be Koin implementation. But "Next time, baby"
object Dependencies {

    fun createMainActivityViewModelFactory(): MainActivityViewModelFactory {
        return MainActivityViewModelFactory()
    }

    fun createProductsFragmentViewModelFactory(): ProductsFragmentViewModelFactory {
        return ProductsFragmentViewModelFactory(getProductsUseCase, productDetailsRepository)
    }

    fun createProductDetailsFragmentViewModelFactory(): ProductDetailsFragmentViewModelFactory {
        return ProductDetailsFragmentViewModelFactory(
            getProductDetailsUseCase,
            productDetailsRepository
        )
    }

    val productDetailsRepository: ProductDetailsRepository by lazy {
        ProductDetailsRepository()
    }

    private val database: LifetechDatabase by lazy {
        Room.databaseBuilder(
            LifetechApp.instance,
            LifetechDatabase::class.java,
            LifetechDatabase.dbName
        ).build()
    }

    private val api: ProductApi by lazy {
        RestService.api
    }

    private val apiDataSource: ApiDataSource by lazy {
        ApiDataSource(api)
    }

    private val dbDataSource: DbDataSource by lazy {
        DbDataSource(database)
    }

    private val productsRepository: ProductsRepository by lazy {
        createProductsRepository(apiDataSource, dbDataSource)
    }

    private fun createProductsRepository(
        apiDataSource: ApiDataSource,
        dbDataSource: DbDataSource
    ): ProductsRepository {
        return ProductsRepositoryImpl(
            apiDataSource,
            dbDataSource
        )
    }

    private val getProductsUseCase by lazy {
        createGetProductsUseCase(productsRepository)
    }

    private fun createGetProductsUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCaseImpl(productsRepository)
    }

    private val getProductDetailsUseCase by lazy {
        createGetProductDetailsUseCase(productsRepository)
    }

    private fun createGetProductDetailsUseCase(productsRepository: ProductsRepository): GetProductDetailsUseCase {
        return GetProductDetailsUseCaseImpl(productsRepository)
    }

}