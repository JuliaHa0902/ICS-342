package edu.metrostate.assignment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime.ofEpochSecond
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ForecastAdapter (private val data:List<DayForecast>): RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_forecast_item, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}
class ForecastViewHolder (view: View): RecyclerView.ViewHolder (view) {
    private val forecastTemp: TextView
    private val forecastDay: TextView
    private val forecastSunRise: TextView
    private val forecastSunSet: TextView
    private val forecastHigh: TextView
    private val forecastLow: TextView

    init {
        forecastTemp = view.findViewById(R.id.forecast_temp)
        forecastDay = view.findViewById(R.id.forecast_day)
        forecastSunRise = view.findViewById(R.id.forecast_sunrise)
        forecastSunSet = view.findViewById(R.id.forecast_sunset)
        forecastHigh = view.findViewById(R.id.forecast_high)
        forecastLow = view.findViewById(R.id.forecast_low)
    }

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
        forecastDay.text = getDate (data.date)
        forecastSunRise.text = String.format("Sunrise: %sam", getTime(data.sunrise))
        forecastSunSet.text = String.format("Sunset: %spm", getTime(data.sunset))
        forecastTemp.text = String.format("Temp: %.0f°", data.temp.day)
        forecastHigh.text = String.format("High: %.0f°", data.temp.max)
        forecastLow.text = String.format("Low: %.0f°", data.temp.min)
    }


}