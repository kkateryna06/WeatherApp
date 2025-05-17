package com.example.weatherapp.data

data class WeatherCurrent(
    val currentTime: String,//
    val weatherDescription: String,//
    val temperature: Double,//
    val feelsLikeTemperature: Double,//
    val uvIndex: Int,//
    val precipitationProbabilityPercent: Int,//
    val precipitationType: String,//
    val thunderstormProbability: Int,//
    val windDirection: String,//
    val windSpeed: Int,//
    val airPressure: Double,//
    val visibility: Int,
    val weatherIconSource: String
)

