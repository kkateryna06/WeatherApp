package com.example.weatherapp.ScreenElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(page: Int) {
    Row(verticalAlignment = Alignment.Bottom) {
        val firstBoxColor = remember { mutableStateOf(Color.LightGray) }
        var secondBoxColor = remember { mutableStateOf(Color.LightGray) }
        var thirdBoxColor = remember { mutableStateOf(Color.LightGray) }
        when (page) {
            0 -> {
                changePage(firstBoxColor, secondBoxColor, thirdBoxColor)
            }
            1 -> {
                changePage(secondBoxColor, firstBoxColor, thirdBoxColor)
            }
            2 -> {
                changePage(thirdBoxColor, firstBoxColor, secondBoxColor)
            }
        }

        Box(modifier = Modifier
            .weight(1f)
            .height(5.dp)
            .background(color = firstBoxColor.value))
        Box(modifier = Modifier
            .weight(1f)
            .height(5.dp)
            .background(color = secondBoxColor.value))
        Box(modifier = Modifier
            .weight(1f)
            .height(5.dp)
            .background(color = thirdBoxColor.value))
    }
}

fun changePage(clickedBox: MutableState<Color>, unclickedBox1: MutableState<Color>, unclickedBox2: MutableState<Color>) {
    clickedBox.value = Color.Gray
    unclickedBox1.value = Color.White
    unclickedBox2.value = Color.White
}

@Preview(showBackground = true)
@Composable
fun PagerIndicatorPrev() {
    PagerIndicator(0)
}