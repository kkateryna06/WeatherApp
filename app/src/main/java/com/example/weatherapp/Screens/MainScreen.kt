package com.example.weatherapp.Screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.weatherapp.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.ScreenElements.CurrentWeatherInfo
import com.example.weatherapp.ScreenElements.PagerIndicator
import com.example.weatherapp.ScreenElements.WeatherByHoursCard
import com.example.weatherapp.ScreenElements.WeatherForAWeekCard
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val currentWeather by viewModel.currentWeather.observeAsState()
    val weeklyForecast by viewModel.weeklyWeather.observeAsState()
    val hourlyForecast by viewModel.hourlyForecast.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
        viewModel.fetchWeeklyForecast()
        viewModel.fetchHourlyForecast()
    }
    Log.d("DEBUG", "weather")

    if (currentWeather == null || weeklyForecast == null || hourlyForecast == null) {
        Column(modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
        }
    }
    else {

        val location = viewModel.location

        val formatter = DateTimeFormatter.ofPattern("dd/MM")
        val instant = Instant.parse(currentWeather!!.currentTime)
        val zoned = instant.atZone(ZoneId.systemDefault())
        val weatherDate = formatter.format(zoned)

        val pagerState = rememberPagerState(pageCount = { 3 })
        val pageNumber = remember { mutableStateOf(0) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = location,
                    fontSize = 30.sp
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.LocationOn, contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "${currentWeather?.temperature?.degrees}°",
                    fontSize = 60.sp,
                )

                Text(
                    text = "${currentWeather!!.weatherCondition.description.text.uppercase()} $weatherDate",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 15.dp, bottom = 10.dp)
                )
            }
            Image(
                painter = painterResource(R.drawable.party_cloudy), contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Column(
                modifier = Modifier.weight(0.5f),
                verticalArrangement = Arrangement.Center
            ) {
                Box {
                    HorizontalPager(
                        state = pagerState, modifier = Modifier.fillMaxHeight(1f)
                    ) { page ->
                        LaunchedEffect(pagerState.currentPage) {
                            pageNumber.value = pagerState.currentPage
                        }
                        if (page == 0) {
                            WeatherByHoursCard(hourlyForecast!!)
                        } else if (page == 1) {
                            CurrentWeatherInfo(currentWeather!!)
                        } else {
                            WeatherForAWeekCard(weatherForecast = weeklyForecast!!)
                        }
                    }
                    Box(
                        modifier = Modifier.align(alignment = Alignment.BottomCenter)
                            .padding(horizontal = 60.dp)
                    ) {
                        PagerIndicator(pageNumber.value)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainWeatherScreenPrev() {
    AppTheme {
        MainScreen(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer))
    }
}