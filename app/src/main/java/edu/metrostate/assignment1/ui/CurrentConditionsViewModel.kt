package edu.metrostate.assignment1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.metrostate.assignment1.models.CurrentConditions
import edu.metrostate.assignment1.models.LatitudeLongtitude
import edu.metrostate.assignment1.service.OpenWeatherMapApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class CurrentConditionsViewModel @Inject constructor (private val api: OpenWeatherMapApi): ViewModel() {
    private val _currentConditions = Channel<CurrentConditions>()

    public val currentConditions: Flow<CurrentConditions> = _currentConditions.receiveAsFlow()

    fun fetchData() = runBlocking {
        val currentConditions = api.getCurrentConditions("55423")
        _currentConditions.trySend(currentConditions)
    }

    fun fetchCurrentLocationData(latitudeLongtitude: LatitudeLongtitude) = runBlocking {
        val currentConditions = api.getCurrentConditions(latitudeLongtitude.latitude, latitudeLongtitude.longtitude)
        _currentConditions.trySend(currentConditions)
    }
}