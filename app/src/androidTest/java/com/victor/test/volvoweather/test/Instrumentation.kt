package com.victor.test.volvoweather.test

import android.os.Bundle
import android.support.test.runner.MonitoringInstrumentation
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore


@CucumberOptions(features = ["features"], glue = ["com.victor.test.volvoweather.test"])
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