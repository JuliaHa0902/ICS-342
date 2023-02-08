package edu.metrostate.assignment1.service

import edu.metrostate.assignment1.models.CurrentConditions
import edu.metrostate.assignment1.models.ForecastData
import edu.metrostate.assignment1.models.LatitudeLongtitude
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("appid") apiKey: String = "7a36fe208b9418b49be93613ef0c8a17",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecastData(
        @Query("zip") zip: String,
        @Query("cnt") cnt: Int = 16,
        @Query("appid") apiKey: String = "7a36fe208b9418b49be93613ef0c8a17",
        @Query("units") units: String = "imperial"
    ) : ForecastData

    @GET("data/2.5/weather")
    suspend fun getCurrentConditions(
        @Query("lat") latitude: Float,
        @Query("lon") longtitude: Float,
        @Query("appid") apiKey: String = "7a36fe208b9418b49be93613ef0c8a17",
        @Query("units") units: String = "imperial"
    ) : CurrentConditions

    @GET("data/2.5/forecast/daily")
    suspend fun getForecastCurrentLocationData(
        @Query("lat") latitude: Float,
        @Query("lon") longtitude: Float,
        @Query("cnt") cnt: Int = 16,
        @Query("appid") apiKey: String = "7a36fe208b9418b49be93613ef0c8a17",
        @Query("units") units: String = "imperial"
    ) : ForecastData
}