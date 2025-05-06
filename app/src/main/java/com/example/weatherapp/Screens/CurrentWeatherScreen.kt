package com.example.weatherapp.Screens

import android.text.format.DateFormat
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.weatherapp.R
import com.example.weatherapp.WeatherCurrent
import com.example.weatherapp.dummyCurrentWeather
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CurrentWeatherScreen(weatherData: WeatherCurrent, location: String, modifier: Modifier = Modifier) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM")
    val instant = Instant.parse(weatherData.currentTime)
    val zoned = instant.atZone(ZoneId.systemDefault())
    val weatherDate = formatter.format(zoned)

    val precipitationType = weatherData.precipitationType.lowercase().replaceFirstChar { it.uppercaseChar() }

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
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Card(modifier = Modifier.weight(1f).padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.7f))) {
                Column(modifier = Modifier.padding(5.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "UV index",
                        fontSize = 18.sp
                    )
                    Text(text = "${weatherData.uvIndex}")
                }
            }
            Card(modifier = Modifier.weight(1f).padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.7f))) {
                Column(modifier = Modifier.padding(5.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Wind speed",
                        fontSize = 18.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                        painter = painterResource(windIconByDirection(weatherData.windDirection)),
                        contentDescription = null
                        )
                        Text(text = "${weatherData.windSpeed} km/h",
                            modifier = Modifier.padding(start = 5.dp))
                    }

                }
            }
            Card(modifier = Modifier.weight(1f).padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.7f))) {
                Column(modifier = Modifier.padding(5.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Precipitation",
                        fontSize = 18.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(precipitationType)
                        Text("${weatherData.precipitationProbabilityPercent}",
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }

                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Button(onClick = {}) {
                    Text(
                        text = "See weather for today"
                    )
                }
            }
            Row {
                Button(onClick = {}) {
                    Text(
                        text = "See weather for a week"
                    )
                }

            }
        }
    }


}

fun windIconByDirection(direction: String): Int {
    var windDirection = direction
    if (direction.contains('_')) {
        windDirection = direction.split('_')[0]
    }

    val icon = when(windDirection) {
        "NORTH" -> R.drawable.ic_north
        "NORTHEAST" -> R.drawable.ic_north_east
        "NORTHWEST" -> R.drawable.ic_north_west
        "SOUTH" -> R.drawable.ic_south
        "SOUTHWEST" -> R.drawable.ic_south_west
        "SOUTHEAST" -> R.drawable.ic_south_east
        "EAST" -> R.drawable.ic_east
        "WEST" -> R.drawable.ic_west

        else -> R.drawable.ic_error
    }
    return icon
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherScreenPrev() {
    AppTheme {
        CurrentWeatherScreen(weatherData = dummyCurrentWeather, location = "Wroclaw, Poland",
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer))
    }
}