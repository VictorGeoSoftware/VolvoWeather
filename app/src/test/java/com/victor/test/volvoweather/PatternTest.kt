package com.victor.test.volvoweather

import com.victor.test.volvoweather.utils.checkIfValueIsWeather
import com.victor.test.volvoweather.utils.trace
import org.junit.Test

/**
 * Created by victorpalmacarrasco on 22/7/18.
 * ${APP_NAME}
 */
class PatternTest {
    @Test
    fun checkWeatherValue() {
        // \+?\d+
        // \d+?\º
        val trial = "27º"
        val trial2 = "27"
        val trial3 = "1º"
        val trial4 = "100º"
        val result = checkIfValueIsWeather(trial4)
        trace("victor - PatternTest - result :: $result")
        assert(result)
    }
}