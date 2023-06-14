package com.example.phonetextfield.data.repository

import android.util.Log
import com.example.phonetextfield.data.models.Response
import com.example.phonetextfield.domain.repository.CountryApi
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api: CountryApi
) {
    companion object {
        const val TAG = "CountryRepository"
    }

    suspend fun getCountryList(): Response {
        Log.d(TAG, "getCountryList")

        val response = try {
            api.getCountryList()
        } catch (exception: Exception) {
            return Response.Error(exception)
        }
        return Response.SuccessAll(response)
    }

    suspend fun getCountry(code: String): Response {
        Log.d(TAG, "getCountry")

        val response = try {
            api.getCountry(code)
        } catch (exception: Exception) {
            return Response.Error(exception)
        }
        return Response.Success(response)
    }

}