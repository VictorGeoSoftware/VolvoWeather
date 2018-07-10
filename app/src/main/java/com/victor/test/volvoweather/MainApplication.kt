package com.victor.test.volvoweather

import android.app.Application
import com.victor.test.volvoweather.di.AppComponent
import com.victor.test.volvoweather.di.AppModule
import com.victor.test.volvoweather.di.DaggerAppComponent


class MainApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }
}