package edu.metrostate.assignment1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.navArgs

import edu.metrostate.assignment1.databinding.FragmentForecastBinding
import edu.metrostate.assignment1.models.DayForecast
import edu.metrostate.assignment1.models.ForecastTemp

class ForecastFragment: Fragment(R.layout.fragment_forecast) {
    private lateinit var binding: FragmentForecastBinding
    private val args:ForecastFragmentArgs by navArgs()
    private val data: List<DayForecast>
    init {
        val firstDay: Long = 1663804118
        val dayTime = 86400
        val timeToNight = 43300
        data = (0..15).map { DayForecast( firstDay + it * dayTime, firstDay + it * dayTime,
            firstDay + it * dayTime + timeToNight, ForecastTemp(70.0F + it, 69.0F, 73.0F + it),
            1023.0F, 90)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)
        binding.forecastList.adapter = ForecastAdapter(data)

    }
}