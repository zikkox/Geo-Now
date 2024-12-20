package com.example.geonow.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}