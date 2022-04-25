package com.salah.amadeus.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salah.amadeus.data.local.entity.DataEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articleEntity: List<DataEntity>): List<Long>

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    @Query("SELECT * FROM data")
    fun getAllCities(): PagingSource<Int, DataEntity>

    @Query("SELECT COUNT(*) from data")
    fun citiesCount(): Int

    @Query("SELECT * FROM data WHERE cityName LIKE '%' || :searchQuery || '%'")
    fun getFilteredCities(searchQuery: String): PagingSource<Int, DataEntity>

}