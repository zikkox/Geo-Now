package com.example.geonow.domain

import com.example.geonow.data.Country
import retrofit2.Response

interface CountryRepository {
    suspend fun getCountry(): List<Country>
}