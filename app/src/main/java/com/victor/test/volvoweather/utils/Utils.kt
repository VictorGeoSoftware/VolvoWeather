package com.victor.test.volvoweather.utils

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.victor.test.volvoweather.BuildConfig
import com.victor.test.volvoweather.MainApplication
import java.text.SimpleDateFormat
import java.util.*


val Activity.app: MainApplication
    get() = application as MainApplication

fun trace(traceToShow: String) {
    System.out.println("VolvoWeather - Traces || $traceToShow")
}

fun prepareWeatherValue(value: Float): String = value.toInt().toString() + "º"

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

fun getDpFromValue(context: Context, value: Int): Int =
        Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value.toFloat(), context.resources.displayMetrics))

fun ImageView.loadWithGlide(icon: String) {
    Glide.with(this).load(BuildConfig.BASE_ICONS_URL + icon + ".png").into(this)
}

fun getFormattedDateTime(time:Long): String {
    val justHour = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = Date(time*1000L)
    return justHour.format(date)
}

fun getFormattedDateTimeForAdapter(time:Long): String {
    val dayNameFormat = SimpleDateFormat("EEEEE", Locale.getDefault())
    val dayNumberFormat = SimpleDateFormat("dd", Locale.getDefault())
    val hourFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = Date(time*1000L)
    val dayNameText = dayNameFormat.format(date)
    val dayNameNumber = dayNumberFormat.format(date)
    val hour = hourFormat.format(date)
    return "$dayNameText $dayNameNumber - $hour"
}