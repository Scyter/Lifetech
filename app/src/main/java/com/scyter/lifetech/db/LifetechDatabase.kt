package com.scyter.lifetech.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scyter.lifetech.db.dao.ProductDao
import com.scyter.lifetech.db.dao.ProductDetailsDao
import com.scyter.lifetech.db.entity.ProductDetailsEntity
import com.scyter.lifetech.db.entity.ProductEntity


@Database(
    entities = [ProductEntity::class, ProductDetailsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LifetechDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun productDetailsDao(): ProductDetailsDao

    companion object {
        const val dbName = "lifetechDB.sqlite"
    }
}