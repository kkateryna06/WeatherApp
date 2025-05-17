package com.example.weatherapp.ScreenElements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.Screens.windIconByDirection
import com.example.weatherapp.data.CurrentWeatherData

@Composable
fun CurrentWeatherInfo(weatherData: CurrentWeatherData) {
    val precipitationType = weatherData.precipitation.probability.type.lowercase().replaceFirstChar { it.uppercaseChar() }

    Column(

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(
                        alpha = 0.7f
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "UV index",
                        fontSize = 18.sp
                    )
                    Text(text = "${weatherData.uvIndex}")
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(
                        alpha = 0.7f
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Wind speed",
                        fontSize = 18.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(
                                windIconByDirection(
                                    weatherData.wind.direction.cardinal
                                )
                            ),
                            contentDescription = null
                        )
                        Text(
                            text = "${weatherData.wind.speed.value} km/h",
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(
                        alpha = 0.7f
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Precipitation",
                        fontSize = 18.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(precipitationType)
                        Text(
                            "${weatherData.precipitation.probability.percent} %",
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(
                        alpha = 0.7f
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Air Pressure",
                        fontSize = 18.sp
                    )
                    Text(text = "${weatherData.airPressure.meanSeaLevelMillibars} hPa")
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(
                        alpha = 0.7f
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Visibility",
                        fontSize = 18.sp
                    )
                    Text(text = "${weatherData.visibility.distance} km")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherInfoPrev() {
//    CurrentWeatherInfo(dummyCurrentWeather)
}