package com.victor.test.volvoweather

import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.victor.test.volvoweather.network.WeatherRepository
import com.victor.test.volvoweather.network.responses.GetForecastResp
import com.victor.test.volvoweather.network.responses.GetWeatherResp
import com.victor.test.volvoweather.presenter.weather.WeatherPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times
import java.sql.Time
import java.util.concurrent.TimeoutException


class WeatherPresenterTest {
    @Mock lateinit var weatherView: WeatherPresenter.WeatherView
    @Mock lateinit var weatherRepository: WeatherRepository
    lateinit var weatherPresenter: WeatherPresenter
    lateinit var testScheduler: TestScheduler

    lateinit var getWeatherResp: GetWeatherResp
    lateinit var getForecastResp: GetForecastResp


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- TEST SET UP ----------------------------------------
    private fun getMockedPresenter(): WeatherPresenter {
        testScheduler = TestScheduler()
        val weatherPresenter = WeatherPresenter(testScheduler, testScheduler, weatherRepository)
        weatherPresenter.view = weatherView

        return weatherPresenter
    }

    private fun loadMockedResponses() {
        val getWeatherRespString = "{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":801," +
                "\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}]," +
                "\"base\":\"stations\",\"main\":{\"temp\":297.53,\"pressure\":1017,\"humidity\":38," +
                "\"temp_min\":293.15,\"temp_max\":300.15},\"visibility\":10000,\"wind\":{\"speed\":7.7," +
                "\"deg\":110},\"clouds\":{\"all\":20},\"dt\":1530634800,\"sys\":{\"type\":1,\"id\":5091," +
                "\"message\":0.0029,\"country\":\"GB\",\"sunrise\":1530589770,\"sunset\":1530649190}," +
                "\"id\":2643743,\"name\":\"London\",\"cod\":200}"
        getWeatherResp = Gson().fromJson(getWeatherRespString, GetWeatherResp::class.java)

        val getForecastRespString = "{  \n" +
                "   \"city\":{  \n" +
                "      \"id\":1851632,\n" +
                "      \"name\":\"Shuzenji\",\n" +
                "      \"coord\":{  \n" +
                "         \"lon\":138.933334,\n" +
                "         \"lat\":34.966671\n" +
                "      },\n" +
                "      \"country\":\"JP\",\n" +
                "      \"cod\":\"200\",\n" +
                "      \"message\":0.0045,\n" +
                "      \"cnt\":38,\n" +
                "      \"list\":[  \n" +
                "         {  \n" +
                "            \"dt\":1406106000,\n" +
                "            \"main\":{  \n" +
                "               \"temp\":298.77,\n" +
                "               \"temp_min\":298.77,\n" +
                "               \"temp_max\":298.774,\n" +
                "               \"pressure\":1005.93,\n" +
                "               \"sea_level\":1018.18,\n" +
                "               \"grnd_level\":1005.93,\n" +
                "               \"humidity\":87,\n" +
                "               \"temp_kf\":0.26\n" +
                "            },\n" +
                "            \"weather\":[  \n" +
                "               {  \n" +
                "                  \"id\":804,\n" +
                "                  \"main\":\"Clouds\",\n" +
                "                  \"description\":\"overcast clouds\",\n" +
                "                  \"icon\":\"04d\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"clouds\":{  \n" +
                "               \"all\":88\n" +
                "            },\n" +
                "            \"wind\":{  \n" +
                "               \"speed\":5.71,\n" +
                "               \"deg\":229.501\n" +
                "            },\n" +
                "            \"sys\":{  \n" +
                "               \"pod\":\"d\"\n" +
                "            },\n" +
                "            \"dt_txt\":\"2014-07-23 09:00:00\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}"
        getForecastResp = Gson().fromJson(getForecastRespString, GetForecastResp::class.java)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        weatherPresenter = getMockedPresenter()
        loadMockedResponses()
    }



    // ---------------------------------------------------------------------------------------------
    // ------------------------------------- TEST CASUISTIC ----------------------------------------
    @Test
    fun `should call to weather request and get some response`() {
        whenever(weatherRepository.getWeather()).thenReturn(Observable.just(getWeatherResp))

        weatherPresenter.callToGetWeather()
        testScheduler.triggerActions()

        verify(weatherView, Times(1)).onWeatherForTodayReceived(getWeatherResp)
    }

    @Test
    fun `should call to weather request and get time out error`() {
        val timeOutError = TimeoutException("TIME_OUT")
        whenever(weatherRepository.getWeather()).thenReturn(Observable.error(timeOutError))

        weatherPresenter.callToGetWeather()
        testScheduler.triggerActions()

        verify(weatherView, Times(1)).onWeatherForTodayError(timeOutError)
    }

    @Test
    fun `should call to forecast request and get some response`() {
        whenever(weatherRepository.getForecast()).thenReturn(Observable.just(getForecastResp))

        weatherPresenter.callToGetForecast()
        testScheduler.triggerActions()

        verify(weatherView, Times(1)).onWeatherForecastReceived(any<GetForecastResp>())
    }

    @Test
    fun `should call to forecast request and retrieve time out error`() {
        val timeOutError = TimeoutException("TIME_OUT")
        whenever(weatherRepository.getForecast()).thenReturn(Observable.error(timeOutError))

        weatherPresenter.callToGetForecast()
        testScheduler.triggerActions()

        verify(weatherView, Times(1)).onWeatherForecastError(timeOutError)
    }
}
