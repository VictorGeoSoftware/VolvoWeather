package com.victor.test.volvoweather.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.victor.test.volvoweather.MainApplication
import com.victor.test.volvoweather.R
import com.victor.test.volvoweather.network.responses.GetForecastResp
import com.victor.test.volvoweather.presenter.weather.WeatherPresenter
import com.victor.test.volvoweather.ui.SpaceDecorator
import com.victor.test.volvoweather.ui.detail.adapters.WeatherForecastAdapter
import com.victor.test.volvoweather.utils.getDpFromValue
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity: AppCompatActivity(), WeatherPresenter.WeatherView {
    @Inject lateinit var weatherPresenter: WeatherPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (application as MainApplication).component.inject(this)

        weatherPresenter.view = this
        weatherPresenter.callToGetForecast()
    }

    override fun onDestroy() {
        super.onDestroy()
        weatherPresenter.onDestroy()
    }


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------- WEATHER VIEW INTERFACE -----------------------------------
    override fun onWeatherForecastReceived(forecast: GetForecastResp) {
        setUpUI(forecast)
    }

    override fun onWeatherForecastError(error: Throwable) {
        Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
    }



    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- METHODS --------------------------------------------
    private fun setUpUI(forecast: GetForecastResp) {
        val linearLayoutManager = LinearLayoutManager(this)
        lstWeatherForecast.layoutManager = linearLayoutManager

        lstWeatherForecast.addItemDecoration(SpaceDecorator(getDpFromValue(this, 10)))

        val weatherForecastAdapter = WeatherForecastAdapter(forecast.list)
        lstWeatherForecast.adapter = weatherForecastAdapter
    }
}