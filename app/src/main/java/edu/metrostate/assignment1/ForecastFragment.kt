package edu.metrostate.assignment1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.navArgs

import edu.metrostate.assignment1.databinding.FragmentForecastBinding

class ForecastFragment: Fragment(R.layout.fragment_forecast) {
    private lateinit var binding: FragmentForecastBinding
    private val args:ForecastFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForecastBinding.bind(view)
        binding.forecastTemp.text = args.forecast.temp
//        binding.forecastList.adapter = ForecastAdapter()

    }
}