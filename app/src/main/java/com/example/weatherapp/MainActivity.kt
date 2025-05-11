package com.example.weatherapp

import android.os.Bundle
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.example.weatherapp.Screens.CurrentWeatherScreen
import com.example.weatherapp.Screens.MainWeatherScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                        MainWeatherScreen(dummyCurrentWeather, location = "Wroclaw, Poland")
                    }
                }
            }
        }

    }
}

