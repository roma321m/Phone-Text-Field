package com.example.phonetextfield

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonetextfield.data.models.Response
import com.example.phonetextfield.data.repository.CountryRepository
import com.example.phonetextfield.domain.models.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneTextFieldViewModel @Inject constructor(
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

    private fun updateSelectedCountry(
        country: Country
    ){
        Log.d(TAG, "updateSelectedCountry")
        selectedCountry = country
    }

    private fun getCountryList() = viewModelScope.launch {
        Log.d(TAG, "getCountryList")
        repository.getCountryList().also { response ->
            if (response is Response.SuccessAll) {
                Log.d(TAG, "${response.data}")
                countryList = response.data
            } else if (response is Response.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

}