package com.example.geonow.data

import retrofit2.Response
import retrofit2.http.GET

interface CountryService {
    @GET("all")
    suspend fun getCountry(): Response<List<Country>>
}