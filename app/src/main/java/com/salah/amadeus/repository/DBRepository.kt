package com.salah.amadeus.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.salah.amadeus.data.local.AppDatabase
import com.salah.amadeus.utils.Transformer.convertDataListToDataEntityList
import com.salah.amadeus.models.Data
import javax.inject.Inject

class DBRepository @Inject constructor(private val appDatabase: AppDatabase) {

    /**
     * inserting all data to db
     */
    suspend fun insertAll(data: List<Data>): List<Long> {
        return appDatabase.cityDao()
            .insertAll(convertDataListToDataEntityList(data))
    }

    /**
     * Getting all cities from local DB Using While using pager for pagination
     * NOTE - Since we are already using LIVE-DATA no need to use suspend function
     */
    fun getAllCities() =
        Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = true,
                maxSize = 100
            )
        ) {
            appDatabase.cityDao().getAllCities()
        }.liveData

    /**
     * Getting filtered list based on user input
     */
    fun getFilteredCities(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 30,
                maxSize = 100,
                enablePlaceholders = false
            )
        ) {
            appDatabase.cityDao().getFilteredCities(query)
        }.liveData

    /**
     * Check if DB has data or not
     */
    fun isDbEmpty(): Boolean {
        return appDatabase.cityDao().citiesCount() == 0
    }


}