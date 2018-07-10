package com.victor.test.volvoweather.ui.detail.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.victor.test.volvoweather.R
import com.victor.test.volvoweather.data.Forecast
import com.victor.test.volvoweather.utils.*
import kotlinx.android.synthetic.main.adapter_weather_forecast.view.*


class WeatherForecastAdapter(private val forecastList: List<Forecast>):
        RecyclerView.Adapter<WeatherForecastAdapter.ForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder(parent.inflate(R.layout.adapter_weather_forecast))
    }

    override fun getItemCount(): Int = forecastList.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(forecast: Forecast) = with(itemView) {
            val dateToShow = getFormattedDateTimeForAdapter(forecast.dt)

            txtDate.text = dateToShow
            txtWeather.text = prepareWeatherValue(forecast.main.temp)

            val humidity = forecast.main.humidity.toInt()
            val humidityText = String.format(itemView.context.getString(R.string.humidity), humidity)
            txtHumidity.text = humidityText

            imgWeatherIcon.loadWithGlide(forecast.weather[0].icon)
            txtTitle.text = forecast.weather[0].main
            txtDescription.text = forecast.weather[0].description

            val dateValues = dateToShow.split(" ")
            val dateDayNumber:Int = dateValues[1].toInt()

            if (dateDayNumber % 2 == 0) {
                mainLayout.setBackgroundColor(context.getColor(R.color.colorAccentAlpha))
            } else {
                mainLayout.setBackgroundColor(context.getColor(R.color.colorPrimaryAlpha))
            }
        }
    }

}