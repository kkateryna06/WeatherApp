package com.example.weatherapp.data

data class WeeklyForecastData(
    val forecastDays: List<ForecastDays>
)

data class ForecastDays(
    val displayDate: DisplayDate,
    val daytimeForecast: DaytimeForecast,
    val maxTemperature: Temperature,
    val minTemperature: Temperature
)

data class DisplayDate(
    val year: Int,
    val month: Int,
    val day: Int
)

data class DaytimeForecast(
    val weatherCondition: WeatherCondition
)