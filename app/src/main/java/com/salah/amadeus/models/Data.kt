package com.salah.amadeus.models

import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("city") val city : City,
	@SerializedName("time") val time : Long,
	@SerializedName("main") val main : Main,
//	@SerializedName("wind") val wind : Wind,
//	@SerializedName("clouds") val clouds : Clouds,
	@SerializedName("weather") val weather : List<Weather>
)