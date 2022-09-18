package edu.metrostate.assignment1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.metrostate.assignment1.databinding.FragmentCurrentConditionsBinding

class CurrentConditionsFragment: Fragment (R.layout.fragment_current_conditions) {
    private lateinit var binding: FragmentCurrentConditionsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCurrentConditionsBinding.bind(view)
        binding.currentTemp.text = "88"
        binding.forecastButton.setOnClickListener {
            val forecast = Forecast("72")
            val action = CurrentConditionsFragmentDirections.actionCurrentConditionsFragmentToForecastFragment(forecast)
            findNavController().navigate(action)
        }
    }
}