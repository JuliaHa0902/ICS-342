package edu.metrostate.assignment1.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.metrostate.assignment1.models.ForecastData
import edu.metrostate.assignment1.models.LatitudeLongtitude
import edu.metrostate.assignment1.service.OpenWeatherMapApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor (private val api: OpenWeatherMapApi): ViewModel() {
    private val _forecastData = Channel<ForecastData>()

    public val forecastData: Flow<ForecastData> = _forecastData.receiveAsFlow()

    fun fetchData() = runBlocking {
        val forecastData = api.getForecastData("55423")
        _forecastData.trySend(forecastData)
    }

    fun fetchCurrentLocationData(latitudeLongtitude: LatitudeLongtitude) = runBlocking {
        val currentConditions = api.getForecastCurrentLocationData(latitudeLongtitude.latitude, latitudeLongtitude.longtitude)
        _forecastData.trySend(currentConditions)
    }
}