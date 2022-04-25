package com.salah.amadeus.di

import com.salah.amadeus.constans.Constants.API_TIME_OUT
import com.salah.amadeus.constans.Constants.BASE_URL
import com.salah.amadeus.data.remote.GzipApi
import com.salah.amadeus.data.remote.GzipInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()
        val okClient = builder.readTimeout(API_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(API_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(API_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(GzipInterceptor())
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okClient).build()

    }

    @Provides
    fun providesGzipApi(retrofit: Retrofit): GzipApi {
        return retrofit.create(GzipApi::class.java)
    }


}