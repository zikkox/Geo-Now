package com.example.geonow.data

import com.example.geonow.domain.CountryRepository
import retrofit2.Response

class CountryRepositoryImpl(private val countryService: CountryService) : CountryRepository {
    override suspend fun getCountry(): List<Country> {
        val response = countryService.getCountry()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Failed to fetch countries: ${response.errorBody()?.string()}")
        }
    }
}
