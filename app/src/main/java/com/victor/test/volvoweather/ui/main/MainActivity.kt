package com.victor.test.volvoweather.ui.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.victor.test.volvoweather.MainApplication
import com.victor.test.volvoweather.R
import com.victor.test.volvoweather.databinding.ActivityMainBinding
import com.victor.test.volvoweather.network.responses.GetWeatherResp
import com.victor.test.volvoweather.presenter.weather.WeatherPresenter
import com.victor.test.volvoweather.ui.detail.DetailActivity
import com.victor.test.volvoweather.utils.getFormattedDateTime
import com.victor.test.volvoweather.utils.loadWithGlide
import com.victor.test.volvoweather.utils.prepareWeatherValue
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), WeatherPresenter.WeatherView {
    @Inject lateinit var weatherPresenter: WeatherPresenter
    private lateinit var mDataBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApplication).component.inject(this)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        weatherPresenter.view = this
        weatherPresenter.callToGetWeather()


        mDataBinding.btnForecast.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        weatherPresenter.onDestroy()
    }



    // ---------------------------------------------------------------------------------------------
    // ---------------------------------- WEATHER VIEW INTERFACE -----------------------------------
    override fun onWeatherForTodayReceived(weather: GetWeatherResp) {
        showWeatherInUI(weather)
    }

    override fun onWeatherForTodayError(error: Throwable) {
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }



    // ---------------------------------------------------------------------------------------------
    // -------------------------------------------- METHODS ----------------------------------------
    private fun showWeatherInUI(weather: GetWeatherResp) {
        mDataBinding.txtCity.text = weather.name
        mDataBinding.txtWeather.text = prepareWeatherValue(weather.main.temp)

        val max = prepareWeatherValue(weather.main.temp_min)
        val min = prepareWeatherValue(weather.main.temp_max)
        val maxMin = String.format(getString(R.string.max_min_weather), min, max)
        mDataBinding.txtWeatherMaxMin.text = maxMin

        imgWeatherIcon.loadWithGlide(weather.weather[0].icon)

        mDataBinding.txtTitle.text = weather.weather[0].main
        mDataBinding.txtDescription.text = weather.weather[0].description

        val humidity = weather.main.humidity.toInt()
        val humidityText = String.format(getString(R.string.humidity), humidity)
        mDataBinding.txtHumidity.text = humidityText

        mDataBinding.txtSunrise.text = String.format(getString(R.string.sunrise), getFormattedDateTime(weather.sys.sunrise))
        mDataBinding.txtSunset.text = String.format(getString(R.string.sunset), getFormattedDateTime(weather.sys.sunset))
    }
}
