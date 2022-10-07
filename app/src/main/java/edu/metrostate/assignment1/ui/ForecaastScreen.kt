package edu.metrostate.assignment1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.metrostate.assignment1.*
import edu.metrostate.assignment1.R

val firstDay = 1663804118L
val dayTime = 86400
val timeToNight = 43300
val forecastData = (0..15).map {
    DayForecast(
        firstDay + it * dayTime,
        firstDay + it * dayTime,
        firstDay + it * dayTime + timeToNight,
        temp = ForecastTemp(70.0F + it, 69.0F, 73.0F + it),
        1023.0F,
        90)
}
@Composable
fun ForecastScreen () {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(13.dp),
    ) {
        items(items = forecastData) {item: DayForecast ->
            ForecastRow(item = item)
        }
    }
}

@Composable
private fun ForecastRow (item: DayForecast) {
    Row(
        modifier = Modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textStyle = TextStyle (
            fontSize = 15.sp
        )
        Image(painter = painterResource(id = R.drawable.sun_icon), contentDescription = "")
        Text(text = item.date.toDate(), style = TextStyle(fontSize = 19.sp))
        Spacer(modifier = Modifier.weight(1f, fill = true))
        Column {
            Text(text = stringResource(id = R.string.item_high, item.temp.max), style = textStyle)
            Text(text = stringResource(id = R.string.item_low, item.temp.min), style = textStyle)
        }
        Spacer(modifier = Modifier.width(24.dp))
        Column (
            horizontalAlignment = Alignment.End
        ){
            Text(text = stringResource(id = R.string.item_sunrise, item.sunrise.toTime()), style = textStyle)
            Text(text = stringResource(id = R.string.item_sunset, item.sunset.toTime()), style = textStyle)
        }
        Spacer(modifier = Modifier.width(24.dp))
    }

}

@Preview
@Composable
private fun ForecastRowPreview () {
//    ForecastRow (item = forecastData[0])
    ForecastScreen()
}