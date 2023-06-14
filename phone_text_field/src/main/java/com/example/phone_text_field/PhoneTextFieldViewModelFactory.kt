package com.example.phone_text_field

import com.example.phone_text_field.data.repository.CountryRepository
import com.example.phone_text_field.domain.repository.CountryApi
import retrofit2.converter.gson.GsonConverterFactory

class PhoneTextFieldViewModelFactory private constructor(
    val phoneTextFieldViewModel: PhoneTextFieldViewModel
) {
    companion object {
        @Volatile
        private var instance: PhoneTextFieldViewModelFactory? = null

        fun getInstance(): PhoneTextFieldViewModelFactory {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        val server = retrofit2.Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl("http://:8080/country/") // Fixme - change ip
                            .build()
                            .create(CountryApi::class.java)
                        instance = PhoneTextFieldViewModelFactory(
                            PhoneTextFieldViewModel(
                                CountryRepository(server)
                            )
                        )
                    }
                }
            }
            return instance!!
        }
    }
}