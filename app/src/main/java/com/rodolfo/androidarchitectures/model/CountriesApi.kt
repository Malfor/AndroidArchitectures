package com.rodolfo.androidarchitectures.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("xdk5-pm3f.json")
    fun getCountries(): Single<List<Country>>
}