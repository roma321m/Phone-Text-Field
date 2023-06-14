package com.example.phonetextfield

sealed class PhoneTextFieldEvent {
    object GetCountryList : PhoneTextFieldEvent()
    class GetCountry(val code: String) : PhoneTextFieldEvent()
}