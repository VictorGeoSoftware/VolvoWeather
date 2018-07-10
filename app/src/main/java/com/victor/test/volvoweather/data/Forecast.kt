package com.victor.test.volvoweather.data


data class Forecast(val dt:Long, val main:Main, val weather: List<Weather>, val clouds: Clouds,
                    val wind: Wind, val rain: RainFall, val snow: RainFall, val dt_txt: String)