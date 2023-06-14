package com.example.phonetextfield.data.models

import com.example.phonetextfield.domain.models.Country

sealed class Response {
    data class Success(val data: Country) : Response()
    data class SuccessAll(val data: List<Country>) : Response()
    data class Error(val error: Throwable) : Response()
}