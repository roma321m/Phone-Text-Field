package com.example.phone_text_field.domain.repository

import com.example.phone_text_field.domain.models.Country
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