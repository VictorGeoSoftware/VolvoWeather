package com.victor.test.volvoweather.utils

import android.app.Activity
import android.os.Looper
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.runner.lifecycle.Stage
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor




/**
 * Created by victorpalmacarrasco on 24/7/18.
 * ${APP_NAME}
 */

fun getCurrentActivity(): Activity? {
    return if (Looper.myLooper() == Looper.getMainLooper()) {
        getCurrentActivityOnMainThread()
    } else {
        val topActivity = arrayOfNulls<Activity>(1)
        getInstrumentation().runOnMainSync(Runnable { topActivity[0] = getCurrentActivityOnMainThread() })
        topActivity[0]
    }
}

private fun getCurrentActivityOnMainThread(): Activity? {
    val registry = ActivityLifecycleMonitorRegistry.getInstance()
    val activities = registry.getActivitiesInStage(Stage.RESUMED)
    return if (activities.iterator().hasNext()) activities.iterator().next() else null
}