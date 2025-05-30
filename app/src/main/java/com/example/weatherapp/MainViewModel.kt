package com.example.weatherapp

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.CurrentWeatherData
import com.example.weatherapp.data.HourlyForecastData
import com.example.weatherapp.data.WeeklyForecastData
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiKey: String,
    private val currentWeatherService: CurrentWeatherApiService,
    private val weeklyForecastService: WeeklyForecastApiService,
    private val hourlyForecastService: HourlyForecastApiService
) : ViewModel() {

    var location = mutableStateOf("Wroclaw, Poland")
    var latitude = 51.090071
    var longitude = 17.035141

    private val _currentWeather = MutableStateFlow<CurrentWeatherData?>(null)
    val currentWeather: StateFlow<CurrentWeatherData?> = _currentWeather

    private val _weeklyForecast = MutableStateFlow<WeeklyForecastData?>(null)
    val weeklyWeather: StateFlow<WeeklyForecastData?> = _weeklyForecast

    private val _hourlyForecast = MutableStateFlow<HourlyForecastData?>(null)
    val hourlyForecast: StateFlow<HourlyForecastData?> = _hourlyForecast

    init {
        fetchWeather()
        fetchWeeklyForecast()
        fetchHourlyForecast()
    }

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = currentWeatherService.getWeather(
                    apiKey = apiKey,
                    latitude = latitude,
                    longitude = longitude
                )
                _currentWeather.value = response

            } catch (e: Exception) {
                Log.d("DEBUG", "Error ${e.message}")
            }
        }
    }

    fun fetchWeeklyForecast() {
        viewModelScope.launch {
            try {
                val response = weeklyForecastService.getForecast(
                    apiKey = apiKey,
                    latitude = latitude,
                    longitude = longitude,
                    days = 5
                )
                _weeklyForecast.value = response
            } catch (e: Exception) {
                Log.d("DEBUG", "Error: ${e.message}")
            }
        }
    }

    fun fetchHourlyForecast() {
        viewModelScope.launch {
            try {
                val response = hourlyForecastService.getForecast(
                    apiKey = apiKey,
                    latitude = latitude,
                    longitude = longitude
                )
                _hourlyForecast.value = response
            } catch (e: Exception) {
                Log.d("DEBUG", "Error: ${e.message}")
            }
        }
    }

    fun updateWeatherLocation(position: LatLng, address: String) {
        location.value = address
        latitude = position.latitude
        longitude = position.longitude
    }
}