package com.example.weatherapp.Screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.weatherapp.HourlyForecast
import com.example.weatherapp.R
import com.example.weatherapp.ScreenElements.CurrentWeatherInfo
import com.example.weatherapp.ScreenElements.PagerIndicator
import com.example.weatherapp.ScreenElements.WeatherByHoursCard
import com.example.weatherapp.ScreenElements.WeatherForAWeekCard
import com.example.weatherapp.WeatherCurrent
import com.example.weatherapp.dummyCurrentWeather
import com.example.weatherapp.dummyWeatherForAWeekList
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainWeatherScreen(weatherData: WeatherCurrent, location: String, modifier: Modifier = Modifier) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM")
    val instant = Instant.parse(weatherData.currentTime)
    val zoned = instant.atZone(ZoneId.systemDefault())
    val weatherDate = formatter.format(zoned)

    val pagerState = rememberPagerState(pageCount = { 3 })
    val pageNumber = remember { mutableStateOf(0) }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = location,
                fontSize = 30.sp
            )
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null,
                    modifier = Modifier.fillMaxSize())
            }
        }
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "${weatherData.temperature}°",
                fontSize = 60.sp,
            )
            Text(
                text = "${weatherData.weatherDescription.uppercase()} $weatherDate",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 15.dp, bottom = 10.dp)
            )
        }
        Image(painter = painterResource(R.drawable.party_cloudy), contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 30.dp))

        Column(modifier = Modifier.weight(0.5f),
            verticalArrangement = Arrangement.Center) {
            Box {
                HorizontalPager(state = pagerState, modifier = Modifier.fillMaxHeight(1f)
                ) {
                    page ->
                    LaunchedEffect(pagerState.currentPage) {
                        pageNumber.value = pagerState.currentPage
                    }
                    if (page == 0) {
                        WeatherByHoursCard()
                    }
                    else if (page == 1) {
                        CurrentWeatherInfo(weatherData)
                    }
                    else {
                        WeatherForAWeekCard(weatherForecast = dummyWeatherForAWeekList)
                    }
                }
                Box(modifier = Modifier.align(alignment = Alignment.BottomCenter).padding(horizontal = 60.dp)) {
                    PagerIndicator(pageNumber.value)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainWeatherScreenPrev() {
    AppTheme {
        MainWeatherScreen(weatherData = dummyCurrentWeather, location = "Wroclaw, Poland",
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer))
    }
}