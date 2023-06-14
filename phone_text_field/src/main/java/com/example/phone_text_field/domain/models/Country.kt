package com.example.phone_text_field.domain.models

import com.google.gson.annotations.SerializedName

data class Country(
    // +972
    @SerializedName("dialCode")
    var dialCode: String? = null,

    // IL
    @SerializedName("code")
    var code: String? = null,

    // Israel
    @SerializedName("country")
    var country: String? = null,

    // https://raw.githubusercontent.com/roma321m/Phone-Text-Field/main/Flags/001-paraguay.svg
    @SerializedName("flagUrl")
    var flagUrl: String? = null,

    // 50-1234567
    @SerializedName("hint")
    var hint: String? = null
)