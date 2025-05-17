package com.example.weatherapp.data

data class HourlyForecastData(
    val forecastHours: List<ForecastHours>,
)

data class ForecastHours(
    val displayDateTime: DisplayDateTime,
    val weatherCondition: WeatherCondition,
    val temperature: Temperature
)

data class DisplayDateTime(
    val hours: Int
)