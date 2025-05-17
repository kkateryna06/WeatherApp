package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.example.compose.AppTheme
import com.example.weatherapp.Screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiKey = assets.open("apiKey.txt").bufferedReader().use { it.readText() }
        Log.d("DEBUG", "Error")
        val factory = WeatherViewModelFactory(apiKey)
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]


        setContent {
            AppTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Scaffold(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                        modifier = Modifier.systemBarsPadding()
                    ) { innerPadding ->
                        MainScreen(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
                    }
                }
            }
        }

    }
}

