package com.rodolfo.androidarchitectures.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val countryName: String
)