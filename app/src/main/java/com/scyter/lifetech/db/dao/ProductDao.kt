package com.scyter.lifetech.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scyter.lifetech.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    fun getProducts(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductEntity>)
}