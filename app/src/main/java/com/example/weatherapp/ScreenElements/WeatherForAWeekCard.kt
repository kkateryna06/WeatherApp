package com.example.weatherapp.ScreenElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastDays
import com.example.weatherapp.data.WeeklyForecastData
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeatherForAWeekCard(weatherForecast: WeeklyForecastData) {
    Card() {
        LazyColumn(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            itemsIndexed(weatherForecast.forecastDays) {
                index, item ->
                WeatherCardRow(item, index)
            }
        }
    }
}

@Composable
fun WeatherCardRow(weather: ForecastDays, index: Int) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 5.dp)
        .clickable {  },
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(R.drawable.ic_mostly_cloudy), contentDescription = null,
            modifier = Modifier.padding(end = 10.dp))
        Text(text = getADayOfTheWeek(
            index = index,
            year = weather.displayDate.year,
            mont = weather.displayDate.month,
            day = weather.displayDate.day
        )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${weather.maxTemperature.degrees}° / ${weather.minTemperature.degrees}°")
    }
}

fun getADayOfTheWeek(index: Int, year: Int, mont: Int, day: Int): String {
    val date = LocalDate.parse("%04d-%02d-%02d".format(year, mont, day))
    val dayOfWeek =
        when (index) {
            0 -> "Today"
            else -> date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()).toString()
        }
    return dayOfWeek
}

@Preview(showBackground = true)
@Composable
fun WeatherForAWeekScreenPrev() {
    val viewModel: MainViewModel = viewModel()

//    WeatherForAWeekCard(
//        viewModel.dummyWeatherForAWeekList
//    )
}