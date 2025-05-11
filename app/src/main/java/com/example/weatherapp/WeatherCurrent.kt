package com.example.weatherapp

data class WeatherCurrent(
    val currentTime: String,
    val weatherDescription: String,
    val temperature: Double,
    val feelsLikeTemperature: Double,
    val uvIndex: Int,
    val precipitationProbabilityPercent: Int,
    val precipitationType: String,
    val thunderstormProbability: Int,
    val windDirection: String,
    val windSpeed: Int,
    val airPressure: Double,
    val visibility: Int,
    val weatherIconSource: String
)

val dummyCurrentWeather = WeatherCurrent(
    currentTime = "2025-05-04T13:30:38.602817875Z",
    weatherDescription = "Partly sunny",
    temperature = 14.4,
    feelsLikeTemperature = 13.0,
    uvIndex = 3,
    precipitationProbabilityPercent = 5,
    precipitationType = "RAIN",
    thunderstormProbability = 0,
    windDirection = "WEST_NORTHWEST",
    windSpeed = 19,
    airPressure = 1007.99,
    visibility = 16,
    weatherIconSource = "https://maps.gstatic.com/weather/v1/party_cloudy"
)

