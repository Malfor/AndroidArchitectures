package com.rodolfo.androidarchitectures.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("municipio") val countryName: String
)