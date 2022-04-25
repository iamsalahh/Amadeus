package com.salah.amadeus.models

import com.google.gson.annotations.SerializedName

data class Wind (

	@SerializedName("speed") val speed : Double,
	@SerializedName("deg") val deg : Double,
	@SerializedName("var_beg") val var_beg : Double,
	@SerializedName("var_end") val var_end : Double
)