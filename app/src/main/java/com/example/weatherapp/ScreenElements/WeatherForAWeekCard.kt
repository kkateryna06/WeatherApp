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
import com.example.weatherapp.WeatherForAWeek
import com.example.weatherapp.dummyWeatherForAWeekList
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeatherForAWeekCard(weatherForecast: List<WeatherForAWeek>) {
    Card() {
        LazyColumn(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            itemsIndexed(weatherForecast) {
                index, item ->
                WeatherCardRow(item, index)
            }
        }
    }
}

@Composable
fun WeatherCardRow(weather: WeatherForAWeek, index: Int) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 5.dp)
        .clickable {  },
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(weather.weatherIconSource), contentDescription = null,
            modifier = Modifier.padding(end = 10.dp))
        Text(text = getADayOfTheWeek(
            index = index,
            year = weather.year,
            mont = weather.month,
            day = weather.day
        )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "${weather.maxTemperature}° / ${weather.minTemperature}°")
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
    WeatherForAWeekCard(
        dummyWeatherForAWeekList
    )
}