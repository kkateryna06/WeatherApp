package com.example.weatherapp

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideApiKey(@ApplicationContext context: Context): String {
        return context.assets.open("apiKey.txt").bufferedReader().use { it.readText() }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://weather.googleapis.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCurrentWeather(retrofit: Retrofit): CurrentWeatherApiService =
        retrofit.create(CurrentWeatherApiService::class.java)

    @Provides
    @Singleton
    fun provideWeeklyForecast(retrofit: Retrofit): WeeklyForecastApiService =
        retrofit.create(WeeklyForecastApiService::class.java)

    @Provides
    @Singleton
    fun provideHourlyForecast(retrofit: Retrofit): HourlyForecastApiService =
        retrofit.create(HourlyForecastApiService::class.java)
}
