package com.scyter.lifetech.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scyter.lifetech.db.entity.ProductDetailsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDetailsDao {

    @Query("SELECT * FROM ProductDetailsEntity where productId = :productId")
    fun getProductDetails(productId: String): Flow<ProductDetailsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFromProduct(product: ProductDetailsEntity)
}