package com.example.weatherapp

import com.example.weatherapp.data.CurrentWeatherData
import com.example.weatherapp.data.HourlyForecastData
import com.example.weatherapp.data.WeeklyForecastData
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApiService {
    @GET("v1/currentConditions:lookup")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("location.latitude") latitude: Double,
        @Query("location.longitude") longitude: Double
    ): CurrentWeatherData
}

interface WeeklyForecastApiService {
    @GET("v1/forecast/days:lookup")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("location.latitude") latitude: Double,
        @Query("location.longitude") longitude: Double,
        @Query("days") days: Int
    ): WeeklyForecastData
}

interface HourlyForecastApiService {
    @GET("v1/forecast/hours:lookup")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("location.latitude") latitude: Double,
        @Query("location.longitude") longitude: Double
    ): HourlyForecastData
}