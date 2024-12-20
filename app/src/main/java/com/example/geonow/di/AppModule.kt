package com.example.geonow.di

import com.example.geonow.data.CountryRepositoryImpl
import com.example.geonow.domain.CountryRepository
import com.example.geonow.presentation.CountryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val AppModule = module {

    // Provide Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide CountryService
    single {
        get<Retrofit>().create(com.example.geonow.data.CountryService::class.java)
    }

    // Provide CountryRepository
    single<CountryRepository> { CountryRepositoryImpl(get()) }

    // Provide ViewModel
    viewModel { CountryViewModel(get()) }
}