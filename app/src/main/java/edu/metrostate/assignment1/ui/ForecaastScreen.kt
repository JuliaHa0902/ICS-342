package edu.metrostate.assignment1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import edu.metrostate.assignment1.*
import edu.metrostate.assignment1.R
import edu.metrostate.assignment1.models.DayForecast
import edu.metrostate.assignment1.models.ForecastItem
import edu.metrostate.assignment1.models.ForecastTemp


@Composable
fun ForecastScreen (
    viewModel: ForecastViewModel = hiltViewModel(),
) {
    val state by viewModel.forecastData.collectAsState(null)
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    state?.let {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(13.dp),
        ) {
            items(items = it.forecastData) {item: ForecastItem ->
                ForecastRow(item = item)
            }
        }
    }
}

@Composable
private fun ForecastRow (item: ForecastItem) {
    Row(
        modifier = Modifier.background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textStyle = TextStyle (
            fontSize = 15.sp
        )
        AsyncImage(
            model = String.format("https://openweathermap.org/img/wn/%s@2x.png", item.weatherData.firstOrNull()?.iconName),
            modifier = Modifier.size(80.dp),
            contentDescription = "Weather Icon")
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
    ForecastScreen()
}