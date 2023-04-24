package com.example.phonetextfield.domain.repository

import com.example.phonetextfield.domain.models.Country
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApi {

    @GET("pathEnd") // TODO path
    suspend fun getCountryList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<Country>

    @GET("pathEnd/{name}") // TODO path
    suspend fun getCountryInfo(
        @Path("name") countryName: String
    ): Country
}