package com.example.weatherapp.data

import com.example.weatherapp.R

data class WeatherForAWeek(
    val day: Int,
    val month: Int,
    val year: Int,
    val weatherIconSource: Int,
    val maxTemperature: Double,
    val minTemperature: Double
)
