package com.salah.amadeus.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.salah.amadeus.data.local.dao.CityDao
import com.salah.amadeus.data.local.entity.DataEntity

@Database(
    version = 1,
    entities = [DataEntity::class],
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
}