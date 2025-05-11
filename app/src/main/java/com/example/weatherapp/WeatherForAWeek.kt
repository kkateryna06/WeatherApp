package com.example.weatherapp

import androidx.compose.ui.res.painterResource

data class WeatherForAWeek(
    val day: Int,
    val month: Int,
    val year: Int,
    val weatherIconSource: Int,
    val maxTemperature: Double,
    val minTemperature: Double
)

val dummyWeatherForAWeekList = listOf(
    WeatherForAWeek(
        day = 9,
        month = 5,
        year = 2025,
        weatherIconSource = R.drawable.ic_mostly_cloudy,
        maxTemperature = 15.3,
        minTemperature = 3.7,
    ),
    WeatherForAWeek(
        day = 10,
        month = 5,
        year = 2025,
        weatherIconSource = R.drawable.ic_drizzle,
        maxTemperature = 15.8,
        minTemperature = 2.4,
    ),
    WeatherForAWeek(
        day = 11,
        month = 5,
        year = 2025,
        weatherIconSource = R.drawable.ic_mostly_cloudy,
        maxTemperature = 13.1,
        minTemperature = 4.1,
    ),
    WeatherForAWeek(
        day = 12,
        month = 5,
        year = 2025,
        weatherIconSource = R.drawable.party_cloudy,
        maxTemperature = 13.3,
        minTemperature = 3.4,
    ),
    WeatherForAWeek(
        day = 13,
        month = 5,
        year = 2025,
        weatherIconSource = R.drawable.ic_mostly_cloudy,
        maxTemperature = 14.8,
        minTemperature = 4.7,
    ),

)
