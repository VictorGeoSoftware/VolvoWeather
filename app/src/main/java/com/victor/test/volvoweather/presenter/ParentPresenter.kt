package com.victor.test.volvoweather.presenter

import com.victor.test.volvoweather.BuildConfig
import com.victor.test.volvoweather.network.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



abstract class ParentPresenter<T1> {
    var view:T1? = null
    var ownRepository: WeatherRepository

    init {
        val okHttpClient = provideOkHttpClient()
        val retrofit = provideRetrofit(okHttpClient, GsonConverterFactory.create(), RxJava2CallAdapterFactory.create(), BuildConfig.BASE_URL)
        ownRepository = retrofit.create(WeatherRepository::class.java)
    }

    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().readTimeout(BuildConfig.TIME_OUT, TimeUnit.SECONDS).connectTimeout(BuildConfig.TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)

        return okHttpClient.build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient, converter: Converter.Factory, callAdapterFactory: RxJava2CallAdapterFactory, baseUrl:String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addCallAdapterFactory(callAdapterFactory).addConverterFactory(converter).build()
    }


    open fun onDestroy() {

    }
}