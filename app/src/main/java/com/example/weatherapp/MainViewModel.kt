package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.HourlyForecast
import com.example.weatherapp.data.WeatherCurrent
import com.example.weatherapp.data.WeatherForAWeek
import com.example.weatherapp.data.CurrentWeatherData
import com.example.weatherapp.data.HourlyForecastData
import com.example.weatherapp.data.WeeklyForecastData
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(private val apiKey: String): ViewModel() {
    val location = "Wroclaw, Poland"

    private val latitude = 51.090071
    private val longitude = 17.035141

    private val _currentWeather = MutableLiveData<CurrentWeatherData>()
    val currentWeather: LiveData<CurrentWeatherData> = _currentWeather

    private val _weeklyForecast = MutableLiveData<WeeklyForecastData>()
    val weeklyWeather: LiveData<WeeklyForecastData> = _weeklyForecast

    private val _hourlyForecast = MutableLiveData<HourlyForecastData>()
    val hourlyForecast: LiveData<HourlyForecastData> = _hourlyForecast

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.currentWeatherService.getWeather(
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
                val response = RetrofitClient.weeklyForecastService.getForecast(
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
                val response = RetrofitClient.hourlyForecast.getForecast(
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
}