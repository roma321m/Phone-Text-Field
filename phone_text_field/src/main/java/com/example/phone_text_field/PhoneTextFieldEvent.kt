package com.example.phone_text_field

import com.example.phone_text_field.domain.models.Country

sealed class PhoneTextFieldEvent {
    object GetCountryList : PhoneTextFieldEvent()
    class UpdateSelectedCountry(val country: Country) : PhoneTextFieldEvent()
}