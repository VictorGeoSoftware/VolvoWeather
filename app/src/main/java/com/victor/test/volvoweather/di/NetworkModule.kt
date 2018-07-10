package com.victor.test.volvoweather.di

import com.victor.test.volvoweather.BuildConfig
import com.victor.test.volvoweather.network.WeatherRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
open class NetworkModule {

    companion object {
        const val NAME_BASE_URL = "NAME_BASE_URL"
    }


    @Provides
    @Named(NAME_BASE_URL)
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().readTimeout(BuildConfig.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(BuildConfig.TIME_OUT, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideCallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, converter: Converter.Factory,
                        callAdapterFactory: RxJava2CallAdapterFactory,
                        @Named(NAME_BASE_URL) baseUrl:String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory).addConverterFactory(converter).build()
    }



    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------   WEB SERVICES   ------------------------------------
    @Provides
    @Singleton
    open fun provideWeatherRepository(retrofit: Retrofit) = retrofit.create(WeatherRepository::class.java)
}