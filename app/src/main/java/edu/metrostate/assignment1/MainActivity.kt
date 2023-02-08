package edu.metrostate.assignment1

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint
import edu.metrostate.assignment1.models.LatitudeLongtitude
import edu.metrostate.assignment1.ui.CurrentConditions
import edu.metrostate.assignment1.ui.ForecastScreen
import edu.metrostate.assignment1.ui.ForecastViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "CurrentConditions") {
                composable("CurrentConditions") {
                    var latitudeLongtitude: LatitudeLongtitude? by remember { mutableStateOf (null) }
                    val onResult = { value: Boolean ->
                        if (ActivityCompat.checkSelfPermission(this@MainActivity, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            fusedLocationProviderClient
                                .getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
                                .addOnSuccessListener { location ->
                                    latitudeLongtitude = LatitudeLongtitude (
                                        latitude = location.latitude.toFloat(),
                                        longtitude = location.longitude.toFloat()
                                    )
                                }
                        }
                    }
                    val requestPermissionLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = onResult
                    )
                    CurrentConditions (
                        latitudeLongtitude = latitudeLongtitude,
                        onGetWeatherForMyLocationClick = {
                            requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
                        }
                    ) {
                        navController.navigate("Forecast")
                    }
                }
                composable("Forecast") {
                    var latitudeLongtitude: LatitudeLongtitude? by remember { mutableStateOf (null) }
                    fusedLocationProviderClient
                        .lastLocation
                        .addOnSuccessListener { location ->
                            latitudeLongtitude = LatitudeLongtitude (
                                latitude = location.latitude.toFloat(),
                                longtitude = location.longitude.toFloat()
                            )
                        }
                    val viewModel = hiltViewModel<ForecastViewModel>()
                    ForecastScreen(viewModel, latitudeLongtitude)
                }
            }
        }
    }
}