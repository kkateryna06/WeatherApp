package com.example.weatherapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.Screens.ChooseLocationScreen
import com.example.weatherapp.Screens.MainScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController(),
               viewModel: MainViewModel = viewModel()
) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.ChooseLocationScreen.route) {
            ChooseLocationScreen(viewModel = viewModel, navController = navController)
        }
    }
}