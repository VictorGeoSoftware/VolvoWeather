package com.victor.test.volvoweather.di

import android.app.Application
import com.victor.test.volvoweather.ui.detail.DetailActivity
import com.victor.test.volvoweather.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, PresenterModule::class])
interface AppComponent {
    fun inject(application: Application)
    fun inject(detailActivity: DetailActivity)
    fun inject(mainActivity: MainActivity)
}