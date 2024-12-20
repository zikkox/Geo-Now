package com.example.geonow.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geonow.data.Country
import com.example.geonow.data.CountryRepositoryImpl
import com.example.geonow.domain.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Sealed class for handling different response states
sealed class Response<out T> {
    object Loading : Response<Nothing>()
    data class Success<T>(val data: T?) : Response<T>()
    data class Error(val exception: Throwable) : Response<Nothing>()
}

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {
    private val _countryFlow = MutableStateFlow<Response<List<Country>>>(Response.Loading)
    val countryFlow: StateFlow<Response<List<Country>>> = _countryFlow

    fun getCountries() = viewModelScope.launch {
        try {
            _countryFlow.value = Response.Loading
            val countriesList = repository.getCountry()
            _countryFlow.value = Response.Success(countriesList)
        } catch (e: Exception) {
            _countryFlow.value = Response.Error(e)
        }
    }
}

