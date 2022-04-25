package com.salah.amadeus.models

import com.google.gson.annotations.SerializedName

data class City (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("findname") val findname : String,
	@SerializedName("country") val country : String,
	@SerializedName("coord") val coord : Coord,
	@SerializedName("zoom") val zoom : Double
)