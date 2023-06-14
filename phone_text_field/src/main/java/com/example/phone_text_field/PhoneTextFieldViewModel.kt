package com.example.phone_text_field

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phone_text_field.data.models.Response
import com.example.phone_text_field.data.repository.CountryRepository
import com.example.phone_text_field.domain.models.Country
import kotlinx.coroutines.launch


class PhoneTextFieldViewModel (
    private val repository: CountryRepository
) : ViewModel() {

    companion object {
        const val TAG = "PhoneTextFieldViewModel"
    }

    var countryList by mutableStateOf(listOf<Country>())
        private set

    var selectedCountry by mutableStateOf(
        Country(
            dialCode = "",
            code = "",
            country = "",
            flagUrl = "",
            hint = ""
        )
    )

    fun handle(event: PhoneTextFieldEvent) {
        when (event) {
            is PhoneTextFieldEvent.UpdateSelectedCountry -> updateSelectedCountry(event.country)
            PhoneTextFieldEvent.GetCountryList -> getCountryList()
        }
    }

    private fun updateSelectedCountry(country: Country) {
        Log.d(TAG, "updateSelectedCountry")
        selectedCountry = country
    }

    private fun getCountryList() = viewModelScope.launch {
        Log.d(TAG, "getCountryList")
        repository.getCountryList().also { response ->
            if (response is Response.SuccessAll) {
                Log.d(TAG, "${response.data}")
                countryList = response.data.sortedBy {
                    it.dialCode
                }
                selectedCountry = countryList.first()
            } else if (response is Response.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

}