package com.victor.test.volvoweather.di

import com.victor.test.volvoweather.network.WeatherRepository
import com.victor.test.volvoweather.presenter.weather.WeatherPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton



@Module(includes = [AppModule::class])
class PresenterModule {

    @Provides
    fun provideWeatherPresenter(weatherRepository: WeatherRepository)
            = WeatherPresenter(AndroidSchedulers.mainThread(), Schedulers.newThread(), weatherRepository)

}