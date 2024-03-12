package com.cs4520.assignment1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cs4520.assignment1.data.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
