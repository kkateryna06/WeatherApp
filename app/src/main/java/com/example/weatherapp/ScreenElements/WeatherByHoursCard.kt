package com.example.weatherapp.ScreenElements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.weatherapp.data.ForecastHours
import com.example.weatherapp.data.HourlyForecastData

@Composable
fun WeatherByHoursCard(weatherForecast: HourlyForecastData) {
    Card(modifier = Modifier.padding(vertical = 40.dp)) {
        LazyRow(modifier = Modifier.weight(1f)) {
            items(weatherForecast.forecastHours) {
                item ->
                WeatherByHoursItem(item)
            }
        }
    }
}

@Composable
fun WeatherByHoursItem(item: ForecastHours) {
    Column(modifier = Modifier.padding(horizontal = 7.dp, vertical = 10.dp).fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${item.displayDateTime.hours}:00")
        Spacer(modifier = Modifier.height(10.dp))
        AsyncImage(
            model = "${item.weatherCondition.iconBaseUri}.svg",
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "${item.temperature.degrees}°")
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherByHoursCardPrev() {
//    val viewModel: MainViewModel = viewModel()
//    WeatherByHoursCard(viewModel.dummyHourlyForecastList)
}