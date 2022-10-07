package edu.metrostate.assignment1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.metrostate.assignment1.R

@Composable
fun CurrentConditions (
    onForecastButtonClick: () -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.city_name),
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
                    text = stringResource(id = R.string.current_temp),
                    style = TextStyle(
                        fontWeight = FontWeight(680),
                        fontSize = 72.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.feel),
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f, fill = true))
            Image(
                modifier = Modifier.size(72.dp),
                painter = painterResource(R.drawable.sun_icon),
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
            Text(text = stringResource(id = R.string.low), style = textStyle)
            Text(text = stringResource(id = R.string.high), style = textStyle)
            Text(text = stringResource(id = R.string.humidity), style = textStyle)
            Text(text = stringResource(id = R.string.pressure), style = textStyle)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onForecastButtonClick) {
            Text(text = stringResource(id = R.string.forecast))
        }
    }
}

@Preview(
    "CurrentCondition",
    device = Devices.PIXEL_4,
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun CurrentConditionsScreenPreview () {
    CurrentConditions {}
}