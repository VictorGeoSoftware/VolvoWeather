package com.victor.test.volvoweather

import android.os.Bundle
import android.support.test.runner.MonitoringInstrumentation
import cucumber.api.android.CucumberInstrumentationCore

/**
 * Created by victorpalmacarrasco on 11/7/18.
 * ${APP_NAME}
 */
class Instrumentation: MonitoringInstrumentation() {
    private val instrumentationCore = CucumberInstrumentationCore(this)

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        instrumentationCore.create(arguments)
        start()
    }

    override fun onStart() {
        super.onStart()

        waitForIdleSync()
        instrumentationCore.start()
    }
}