package com.example.weatherapp.Screens

import android.content.Context
import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.MainViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import java.util.Locale

@Composable
fun ChooseLocationScreen(viewModel: MainViewModel, navController: NavController) {
    var selectedPosition by remember { mutableStateOf<LatLng?>(LatLng(viewModel.latitude, viewModel.longitude)) }
    var addressString by remember { viewModel.location }

    val context = LocalContext.current

    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            OutlinedTextField(
                onValueChange = {
                    addressString = it
                },
                value = addressString
            )
            IconButton(onClick = {
                val latlng = getLocationFromAddress(context, addressString)
                if (latlng != null) {
                    selectedPosition = LatLng(latlng.first, latlng.second)
                }
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        }
        Button(
            onClick = {
                if (selectedPosition != null) {
                    viewModel.updateWeatherLocation(
                        position = selectedPosition!!,
                        address = addressString
                    )
                }
                navController.navigateUp()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save Location")
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            onMapClick = {
                selectedPosition = it
                addressString = getAddressFromLatLng(context, selectedPosition!!)
            }
        ) {
            if (selectedPosition != null) {
                Marker(
                    state = MarkerState(position = selectedPosition!!)
                )
            }
        }
    }
}

fun getAddressFromLatLng(context: Context, position: LatLng): String {
    val address = Geocoder(context)
        .getFromLocation(position.latitude, position.longitude,1 )?.firstOrNull()
    return if (address?.locality != null) {
        "${address.locality}, ${address.countryName}"
    }
    else if (address?.adminArea != null) {
        "${address.adminArea}, ${address.countryName}"
    }
    else {
        "Select a valid location"
    }
}

fun getLocationFromAddress(context: Context, address: String): Pair<Double, Double>? {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = geocoder.getFromLocationName(address, 1)
    if (addresses != null && addresses.isNotEmpty()) {
        val location = addresses[0]
        return Pair(location.latitude, location.longitude)
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun ChooseLocationScreenPrev() {
//    ChooseLocationScreen(viewModel = viewModel())
}