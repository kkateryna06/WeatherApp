package com.example.weatherapp

data class HourlyForecast(
    val year: Int,
    val mont: Int,
    val day: Int,
    val hours: Int,
    val weatherIconSource: Int,
    val temperature: Double,
)

val dummyHourlyForecastList = listOf(
    HourlyForecast(
        year = 2025,
        mont = 5,
        day = 11,
        hours = 21,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
    HourlyForecast(
        year = 2025,
        mont = 5,
        day = 11,
        hours = 22,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
    HourlyForecast(
        year = 2025,
        mont = 5,
        day = 11,
        hours = 23,
        weatherIconSource = R.drawable.mostly_clear,
        temperature = 9.8
    ),
    HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 0,
        weatherIconSource = R.drawable.clear,
        temperature = 9.8
    ),
    HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 1,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 1,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 1,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 1,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 1,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 1,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),
HourlyForecast(
        year = 2025,
        mont = 5,
        day = 12,
        hours = 1,
        weatherIconSource = R.drawable.partly_clear,
        temperature = 9.8
    ),

)
