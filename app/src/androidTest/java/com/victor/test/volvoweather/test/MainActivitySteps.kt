package com.victor.test.volvoweather.test

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.victor.test.volvoweather.R
import com.victor.test.volvoweather.ui.main.MainActivity
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule

class MainActivitySteps {
    @Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var activity:Activity

    @Before
    fun setUp() {
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
        assertNotNull(activity)
    }

    @When("^I see my MainActivity")
    fun I_see_my_MainActivity() {
        onView(withId(R.id.txtCity)).check(matches(isDisplayed()))
    }

    @Then("^I should wait for results")
    fun I_should_wait_for_results() {
        onView(withId(R.id.txtWeather)).check(matches(withText("27º")))
    }


}