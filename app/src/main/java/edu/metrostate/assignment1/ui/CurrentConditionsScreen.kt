package edu.metrostate.assignment1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import edu.metrostate.assignment1.R
import edu.metrostate.assignment1.models.CurrentConditions
import edu.metrostate.assignment1.models.LatitudeLongtitude

@Composable
fun CurrentConditions (
    latitudeLongtitude: LatitudeLongtitude?,
    viewModel: CurrentConditionsViewModel = hiltViewModel(),
    onGetWeatherForMyLocationClick:() -> Unit,
    onForecastButtonClick: () -> Unit
) {
    val state by viewModel.currentConditions.collectAsState(null)
    if (latitudeLongtitude != null) {
        LaunchedEffect(Unit) {
            viewModel.fetchCurrentLocationData(latitudeLongtitude)
        }
    } else {
        LaunchedEffect(Unit) {
            viewModel.fetchData()
        }
    }
    state?.let {
        CurrentConditionsContent(it, onGetWeatherForMyLocationClick, onForecastButtonClick)
    }

}

@Composable
private fun CurrentConditionsContent(
    currentConditions: CurrentConditions,
    onGetWeatherForMyLocationClick:() -> Unit,
    onForecastButtonClick: () -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentConditions.cityName,
            style = TextStyle(
                fontWeight = FontWeight(680),
                fontSize = 17.sp
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.current_temp, currentConditions.conditions.temperature.toInt()),
                    style = TextStyle(
                        fontWeight = FontWeight(680),
                        fontSize = 72.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.feel, currentConditions.conditions.feelsLike.toInt()),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f, fill = true))
            AsyncImage(
                model = String.format("https://openweathermap.org/img/wn/%s@2x.png", currentConditions.weatherData.firstOrNull()?.iconName),
                modifier = Modifier.size(120.dp),
                contentDescription = "Sunny")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            val textStyle = TextStyle(
                fontSize = 17.sp
            )
            Text(text = stringResource(id = R.string.low, currentConditions.conditions.minTemperature.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.high, currentConditions.conditions.maxTemperature.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.humidity,currentConditions.conditions.humidity.toInt()), style = textStyle)
            Text(text = stringResource(id = R.string.pressure, currentConditions.conditions.pressure.toInt()), style = textStyle)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onForecastButtonClick) {
            Text(text = stringResource(id = R.string.forecast))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onGetWeatherForMyLocationClick) {
            Text(text = stringResource(id = R.string.get_weather_for_my_location))
        }
    }
}

//@Preview(
//    "CurrentCondition",
//    device = Devices.PIXEL_4,
//    showBackground = true,
//    showSystemUi = true,
//)
//@Composable
//fun CurrentConditionsScreenPreview () {
//    CurrentConditions {}
//}