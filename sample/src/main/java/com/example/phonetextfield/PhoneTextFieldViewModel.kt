package com.example.phonetextfield

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phonetextfield.data.models.Response
import com.example.phonetextfield.data.repository.CountryRepository
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

    var areaCodeList = mutableStateOf(listOf("a", "b", "c"))
        private set

    fun handle(event: PhoneTextFieldEvent) {
        when (event) {
            is PhoneTextFieldEvent.GetCountry -> getCountry(event.code)
            PhoneTextFieldEvent.GetCountryList -> getCountryList()
        }
    }

    private fun getCountry(
        code: String
    ) = viewModelScope.launch {
        Log.d(TAG, "getCountry")
    }

    private fun getCountryList() = viewModelScope.launch {
        Log.d(TAG, "getCountryList")
        repository.getCountryList().also { response ->
            if (response is Response.SuccessAll) {
                response.data // fixme - use
                Log.d(TAG, "${response.data}")
            } else if (response is Response.Error) {
                response.apply {
                    Log.e(TAG, "${error.message}")
                }
            }
        }
    }

}