package com.victor.test.volvoweather.di

import com.victor.test.volvoweather.di.scopes.ViewScope
import com.victor.test.volvoweather.presenter.weather.WeatherPresenter
import dagger.Component
import javax.inject.Singleton



//@Singleton
@ViewScope
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(weatherPresenter: WeatherPresenter)
}