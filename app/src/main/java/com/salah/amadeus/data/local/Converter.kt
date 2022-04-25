package com.salah.amadeus.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.salah.amadeus.data.local.entity.CityEntity
import com.salah.amadeus.data.local.entity.MainEntity
import com.salah.amadeus.data.local.entity.WeatherEntity
import com.salah.amadeus.models.Coord

class Converter {

    val gson = Gson()

    @TypeConverter
    fun cityEntityToString(sourceEntity: CityEntity): String {

        val type = object : TypeToken<CityEntity>() {}.type
        return gson.toJson(sourceEntity, type)
    }

    @TypeConverter
    fun fromStringToCityEntity(string: String): CityEntity {

        val type = object : TypeToken<CityEntity>() {}.type
        return gson.fromJson<CityEntity>(string, type)

    }

    @TypeConverter
    fun fromStringToCoordEntity(string: String): Coord {

        val type = object : TypeToken<Coord>() {}.type
        return gson.fromJson<Coord>(string, type)

    }

    @TypeConverter
    fun coordEntityToString(sourceEntity: Coord): String {

        val type = object : TypeToken<Coord>() {}.type
        return gson.toJson(sourceEntity, type)
    }

    @TypeConverter
    fun weatherEntityToString(sourceEntity: List<WeatherEntity>): String {

        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return gson.toJson(sourceEntity, type)
    }

    @TypeConverter
    fun fromStringToWeatherEntity(string: String): List<WeatherEntity> {

        val type = object : TypeToken<List<WeatherEntity>>() {}.type
        return gson.fromJson(string, type)

    }

    @TypeConverter
    fun mainEntityToString(sourceEntity: MainEntity): String {

        val type = object : TypeToken<MainEntity>() {}.type
        return gson.toJson(sourceEntity, type)
    }

    @TypeConverter
    fun fromStringToMainEntity(string: String): MainEntity {

        val type = object : TypeToken<MainEntity>() {}.type
        return gson.fromJson(string, type)

    }


}