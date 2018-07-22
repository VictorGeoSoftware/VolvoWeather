package com.victor.test.volvoweather.test

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.victor.test.volvoweather.R
import com.victor.test.volvoweather.ui.main.MainActivity
import com.victor.test.volvoweather.utils.trace
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import android.content.res.Resources.NotFoundException
import android.widget.TextView
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.AppCompatTextView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import android.widget.EditText
import com.victor.test.volvoweather.utils.checkIfValueIsWeather
import org.hamcrest.TypeSafeMatcher


class MainActivitySteps {
    @Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private lateinit var activity: Activity


    @Before
    fun setUp() {
        trace("victor - MainActivitySteps - setUp!")
        activityTestRule.launchActivity(Intent())
        activity = activityTestRule.activity
    }

    @After
    fun tearDown() {
        activityTestRule.finishActivity()
    }


    // ---------------------------------------------------------------------------------------------
    // --------------------------------------------- TEST CASUISTIC --------------------------------
    @Given("^I have my MainActivity")
    fun I_have_my_MainActivity() {
        trace("victor - I_have_my_MainActivity!")
        assertNotNull(activity)
    }

    @When("^I see my MainActivity")
    fun I_see_my_MainActivity() {
        trace("victor - I_see_my_MainActivity!")
        onView(withId(R.id.txtCity)).check(matches(isDisplayed()))
    }

    @Then("^I should (true|false) current weather forecast")
    fun I_should_see_current_weather_forecast(see:Boolean) {
        trace("victor - I_should_wait_for_results! $see")

        if (see) {
            onView(withId(R.id.txtWeatherMain)).check(matches(hasRightWeatherValue()))
        } else {
            onView(withId(R.id.txtWeatherMain)).check(matches(hasRightWeatherValue()))
        }
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
}