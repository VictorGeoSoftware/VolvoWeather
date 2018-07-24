package com.victor.test.volvoweather.test

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.victor.test.volvoweather.R
import com.victor.test.volvoweather.test.MainActivitySteps.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.victor.test.volvoweather.ui.detail.DetailActivity
import com.victor.test.volvoweather.ui.main.MainActivity
import com.victor.test.volvoweather.utils.checkIfValueIsWeather
import com.victor.test.volvoweather.utils.getCurrentActivity
import com.victor.test.volvoweather.utils.trace
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import junit.framework.Assert.assertNotNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import kotlin.reflect.jvm.jvmName


class MainActivitySteps {
    @Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private lateinit var activity: Activity

    private var detailActivity: DetailActivity? = null


    @Before
    fun setUp() {
        Intents.init()
        trace("victor - MainActivitySteps - setUp!")
        activityTestRule.launchActivity(Intent())
        activity = activityTestRule.activity
    }

    @After
    private fun tearDown() {
        Intents.release()
        activityTestRule.finishActivity()
    }


    // ---------------------------------------------------------------------------------------------
    // --------------------------------------------- TEST CASUISTIC --------------------------------
    @Given("^I have my MainActivity")
    fun I_have_my_MainActivity() {
        assertNotNull(activity)
    }

    @When("^I see my MainActivity")
    fun I_see_my_MainActivity() {
        onView(withId(R.id.txtCity)).check(matches(isDisplayed()))
    }

    @And("^I press weather forecast button$")
    fun I_press_weather_forecast_button() {
        onView(withId(R.id.btnForecast)).perform(click())

        // com.victor.test.volvoweather.ui.detail.DetailActivity

        val name = DetailActivity::class.jvmName
        trace("victor - I_press_weather_forecast_button - name :: $name")
        intended(hasComponent(DetailActivity::class.jvmName))
        detailActivity = getCurrentActivity() as DetailActivity

        trace("victor - I_press_weather_forecast_button - detailActivity :: $detailActivity")
    }

    @And("^I see the DetailActivity")
    fun I_see_the_DetailActivity() {
        onView(withId(R.id.txtWeatherForecastTitle)).check(matches(isCompletelyDisplayed()))
    }

    @Then("^I should (true|false) current weather forecast")
    fun I_should_see_current_weather_forecast(see:Boolean) {

//        if (see) {
//            onView(withId(R.id.txtWeatherMain)).check(matches(hasRightWeatherValue()))
//        } else {
//            onView(withId(R.id.txtWeatherMain)).check(matches(hasRightWeatherValue()))
//        }


        onView(withId(R.id.lstWeatherForecast)).check(withItemCount(greaterThan(5)))

        // Important for avoid expetions because DetailActivity on main thread
        detailActivity?.finish()
        tearDown()
    }



    // ---------------------------------------------–-----------------------------------------------
    // --------------------------------------------- METHODS ---------------------------------------
    private fun hasRightWeatherValue(): Matcher<View> {

        return object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) {
                description.appendText("TextView value has to be in weather info format")
            }

            override fun matchesSafely(view: View?): Boolean {
                if (view !is TextView && view !is AppCompatTextView && view !is EditText) {
                    return false
                }
                if (view != null) {
                    val text: String = (view as? TextView)?.text?.toString() ?: if (view is AppCompatTextView) {
                        trace("victor - hasValueEqualsTo - TextView :: ${view.text}")
                        view.text.toString()
                    } else {
                        trace("victor - hasValueEqualsTo - EditText :: ${(view as EditText).text}")
                        (view as EditText).text.toString()
                    }

                    trace("victor - hasValueEqualsTo - result :: $text")
//                    return text.equals(content, ignoreCase = true)
                    return checkIfValueIsWeather(text)
                }
                return false
            }
        }
    }

    private class RecyclerViewItemCountAssertion: ViewAssertion {
        lateinit var matcher: Matcher<Int>


        constructor(matcher: Matcher<Int>) {
            this.matcher = matcher
        }


        companion object {
            fun withMyItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
                return withItemCount(`is`(expectedCount))
            }

            fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
                return RecyclerViewItemCountAssertion(matcher)
            }
        }



        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter.itemCount, matcher)
        }

    }
}