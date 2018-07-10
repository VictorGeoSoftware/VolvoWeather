package com.victor.test.volvoweather.presenter.weather

import com.victor.test.volvoweather.network.WeatherRepository
import com.victor.test.volvoweather.network.responses.GetForecastResp
import com.victor.test.volvoweather.network.responses.GetWeatherResp
import com.victor.test.volvoweather.presenter.ParentPresenter
import com.victor.test.volvoweather.utils.trace
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable


class WeatherPresenter(private val androidSchedulers: Scheduler,
                       private val subscriberSchedulers: Scheduler,
                       private var weatherRepository: WeatherRepository):
        ParentPresenter<WeatherPresenter.WeatherView>() {

    private var weatherDisposable: Disposable? = null
    private var forecastDisposable: Disposable? = null



    // ---------------------------------------------------------------------------------------------
    // --------------------------------------- PRESENTER VIEW INTERFACE ----------------------------
    interface WeatherView {
        fun onWeatherForTodayReceived(weather: GetWeatherResp) { }
        fun onWeatherForTodayError(error: Throwable) { }
        fun onWeatherForecastReceived(forecast: GetForecastResp) { }
        fun onWeatherForecastError(error: Throwable) { }
    }


    // ---------------------------------------------------------------------------------------------
    // --------------------------------------- PRESENTER METHODS -----------------------------------


    fun callToGetWeather() {
        weatherDisposable = weatherRepository.getWeather()
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe(
                        {
                            view?.onWeatherForTodayReceived(it)
                        },
                        {
                            it.printStackTrace()
                            view?.onWeatherForTodayError(it)
                        },
                        {
                            System.out.println("WeatherPresenter - onComplete")
                        }
                )
    }

    fun callToGetForecast() {
        forecastDisposable = weatherRepository.getForecast()
                .observeOn(androidSchedulers)
                .subscribeOn(subscriberSchedulers)
                .subscribe(
                        {
                            System.out.println("WeatherPresenter - callToGetForecast - onResponse:: $it")
                            view?.onWeatherForecastReceived(it)
                        },
                        {
                            System.out.println("WeatherPresenter - callToGetForecast - onError:: ${it.localizedMessage}")
                            view?.onWeatherForecastError(it)
                        },
                        {
                            System.out.println("WeatherPresenter - callToGetForecast - onComplete")
                        }
                )
    }

    override fun onDestroy() {
        view = null

        if (weatherDisposable?.isDisposed == true) {
            weatherDisposable?.dispose()
        }

        if (forecastDisposable?.isDisposed == true) {
            forecastDisposable?.dispose()
        }
    }


}