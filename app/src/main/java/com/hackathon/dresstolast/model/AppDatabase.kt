package com.hackathon.dresstolast.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ReviewQuestion::class, Brand::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun reviewQuestionDao(): ReviewQuestionDao
    abstract fun brandDao(): BrandDao
}