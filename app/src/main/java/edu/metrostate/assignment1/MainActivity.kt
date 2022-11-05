package edu.metrostate.assignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.metrostate.assignment1.ui.CurrentConditions
import edu.metrostate.assignment1.ui.ForecastScreen
import edu.metrostate.assignment1.ui.ForecastViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "CurrentConditions") {
                composable("CurrentConditions") {
                    CurrentConditions{
                        navController.navigate("Forecast")
                    }
                }
                composable("Forecast") {
                    val viewModel = hiltViewModel<ForecastViewModel>()
                    ForecastScreen(viewModel)
                }
            }
        }
    }
}