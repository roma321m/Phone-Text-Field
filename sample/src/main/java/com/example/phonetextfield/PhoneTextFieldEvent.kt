package com.example.phonetextfield

import com.example.phonetextfield.domain.models.Country

sealed class PhoneTextFieldEvent {
    object GetCountryList : PhoneTextFieldEvent()
    class UpdateSelectedCountry(val country: Country) : PhoneTextFieldEvent()
}