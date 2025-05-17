package com.example.weatherapp.data

data class CurrentWeatherData(
    val currentTime: String,
    val weatherCondition: WeatherCondition,
    val temperature: Temperature,
    val feelsLikeTemperature: Temperature,
    val relativeHumidity: Int,
    val uvIndex: Int,
    val precipitation: Precipitation,
    val thunderstormProbability: Int,
    val airPressure: AirPressure,
    val wind: Wind,
    val visibility: Visibility
)

data class WeatherCondition(
    val iconBaseUri: String,
    val description: Description
)

data class Description(
    val text: String
)

data class Temperature(
    val degrees: Double
)

data class Precipitation(
    val probability: Probability
)

data class Probability(
    val percent: Int,
    val type: String
)

data class AirPressure(
    val meanSeaLevelMillibars: Double
)

data class Wind(
    val direction: Direction,
    val speed: Speed
)

data class Direction(
    val cardinal: String
)

data class Speed(
    val value: Int
)

data class Visibility(
    val distance: Int
)