package com.example.weatherapp

sealed class Screen (val route: String) {
    object MainScreen: Screen("main_screen")
    object ChooseLocationScreen: Screen("choose_location_screen")
}
