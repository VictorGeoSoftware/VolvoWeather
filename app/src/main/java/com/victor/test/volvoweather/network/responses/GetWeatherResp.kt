package com.victor.test.volvoweather.network.responses

import com.victor.test.volvoweather.data.*


data class GetWeatherResp(val coord: Coord, val weather: ArrayList<Weather>, val main: Main, val wind: Wind,
                          val clouds: Clouds, val rain: RainFall, val snow: RainFall, val dt: Long,
                          val sys: Sys, val id: Long, val name: String)