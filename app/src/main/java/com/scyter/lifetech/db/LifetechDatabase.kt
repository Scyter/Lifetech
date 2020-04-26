package com.scyter.lifetech.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scyter.lifetech.db.dao.ProductDao
import com.scyter.lifetech.db.entity.ProductEntity


@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class LifetechDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}