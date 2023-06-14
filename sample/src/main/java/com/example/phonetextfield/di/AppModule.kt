package com.example.phonetextfield.di

import com.example.phonetextfield.data.repository.CountryRepository
import com.example.phonetextfield.domain.repository.CountryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCountryRepository(
        api: CountryApi
    ) = CountryRepository(api)

    @Singleton
    @Provides
    fun provideCountryApi() : CountryApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://ip:8080/country/") // Fixme - change ip
            .build()
            .create(CountryApi::class.java)
    }
}