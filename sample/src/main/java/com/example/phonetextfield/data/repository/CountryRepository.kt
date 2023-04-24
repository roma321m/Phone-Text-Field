package com.example.phonetextfield.data.repository

import android.util.Log
import com.example.phonetextfield.data.models.ResponseState
import com.example.phonetextfield.domain.models.Country
import com.example.phonetextfield.domain.repository.CountryApi
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api: CountryApi
) {
    companion object {
        const val TAG = "CountryRepository"
    }

    suspend fun getCountryList(limit: Int, offset: Int): ResponseState<List<Country>> {
        Log.d(TAG, "getCountryList")

        val response = try {
            api.getCountryList(limit, offset)
        } catch (exception: Exception) {
            return ResponseState.Error(exception)
        }
        return ResponseState.Success(response)
    }

    suspend fun getCountry(name: String): ResponseState<Country> {
        Log.d(TAG, "getCountry")

        val response = try {
            api.getCountryInfo(name)
        } catch (exception: Exception) {
            return ResponseState.Error(exception)
        }
        return ResponseState.Success(response)
    }

}