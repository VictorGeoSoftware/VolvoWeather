package com.victor.test.volvoweather.network

import com.victor.test.volvoweather.BuildConfig
import com.victor.test.volvoweather.network.responses.GetForecastResp
import com.victor.test.volvoweather.network.responses.GetWeatherResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


interface WeatherRepository {
    @Headers("Content-Type: application/json;charset=UTF-8")

    @GET("data/2.5/weather?lat=" + BuildConfig.MY_LATITUDE + "&lon=" + BuildConfig.MY_LONGITUDE
            + "&units=metric" + "&APPID=" + BuildConfig.API_KEY)
    fun getWeather(): Observable<GetWeatherResp>

    @GET("data/2.5/forecast?lat=" + BuildConfig.MY_LATITUDE + "&lon=" + BuildConfig.MY_LONGITUDE
            + "&units=metric" + "&APPID=" + BuildConfig.API_KEY)
    fun getForecast(): Observable<GetForecastResp>
}