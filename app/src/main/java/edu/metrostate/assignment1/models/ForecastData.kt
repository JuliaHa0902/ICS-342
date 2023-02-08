package edu.metrostate.assignment1.models

import com.squareup.moshi.Json

data class TempData (
    @Json(name = "day") val day: Float,
    @Json(name = "min") val min: Float,
    @Json(name = "max") val max: Float,
)

data class IconData (
    @Json(name = "icon") val iconName: String,
)

data class ForecastItem (
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "pressure") val pressure: Float,
    @Json(name = "humidity") val humidity: Float,
    @Json(name = "temp") val temp: TempData,
    @Json(name = "weather") val weatherData: List<IconData>,
)

data class CityData (
    @Json(name = "name") val name: String
)

data class ForecastData(
    @Json(name = "city") val city: CityData,
    @Json(name = "list") val forecastData:List<ForecastItem>,
)