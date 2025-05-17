package com.example.weatherapp.data


data class HourlyForecast(
    val year: Int,
    val mont: Int,
    val day: Int,
    val hours: Int,
    val weatherIconSource: Int,
    val temperature: Double,
)
