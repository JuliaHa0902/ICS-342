package edu.metrostate.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.res.Resources
import edu.metrostate.assignment1.databinding.ViewForecastItemBinding
import java.time.LocalDateTime.ofEpochSecond
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastAdapter (private val data:List<DayForecast>): RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(ViewForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}
class ForecastViewHolder (private val binding: ViewForecastItemBinding): RecyclerView.ViewHolder (binding.root) {

    private fun getDate(dateTimeStamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        val dateTime = ofEpochSecond(dateTimeStamp, 0, ZoneOffset.of("-5"))
        return formatter.format(dateTime)
    }

    private fun getTime(dateTimeStamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("h:mm")
        val dateTime = ofEpochSecond(dateTimeStamp, 0, ZoneOffset.of("-5"))
        return formatter.format(dateTime)
    }

    fun bind (data: DayForecast) {
        val resources = binding.root.context.resources
        binding.forecastDay.text = getDate (data.date)
        binding.forecastSunrise.text = resources.getString(R.string.item_sunrise, getTime (data.sunrise))
        binding.forecastSunset.text = resources.getString(R.string.item_sunset, getTime (data.sunset))
        binding.forecastTemp.text = resources.getString(R.string.item_temp, data.temp.day)
        binding.forecastHigh.text = resources.getString(R.string.item_high, data.temp.max)
        binding.forecastLow.text = resources.getString(R.string.item_low, data.temp.min)
    }
}