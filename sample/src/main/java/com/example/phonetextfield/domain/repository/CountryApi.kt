package com.example.phonetextfield.domain.repository

import com.example.phonetextfield.domain.models.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApi {

    @GET("all")
    suspend fun getCountryList(): List<Country>

    @GET
    suspend fun getCountry(
        @Query("code") countryCode: String
    ): Country
}