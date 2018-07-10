package com.victor.test.volvoweather.network.responses

import com.victor.test.volvoweather.data.City
import com.victor.test.volvoweather.data.Forecast


data class GetForecastResp(val city:City, val cnt:Int, val list:List<Forecast>)