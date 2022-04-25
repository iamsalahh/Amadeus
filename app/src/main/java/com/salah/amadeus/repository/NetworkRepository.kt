package com.salah.amadeus.repository

import com.salah.amadeus.data.remote.GzipApi
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val api: GzipApi) {

    /**
     * Getting Json from Remote server
     */
    suspend fun getData(): Response<ResponseBody> {
        return api.getWeathers()
    }

}