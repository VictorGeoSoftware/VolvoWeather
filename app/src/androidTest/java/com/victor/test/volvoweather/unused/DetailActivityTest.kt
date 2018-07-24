package com.victor.test.volvoweather.unused

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.victor.test.volvoweather.ui.detail.DetailActivity
import com.victor.test.volvoweather.ui.main.MainActivity
import com.victor.test.volvoweather.R
import com.victor.test.volvoweather.utils.trace
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import junit.framework.Assert.assertNotNull
import org.junit.Rule


/**
 * Created by victorpalmacarrasco on 23/7/18.
 * ${APP_NAME}
 */
class DetailActivityTest {
    @Rule
    val mainActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    private lateinit var mainActivity: MainActivity

    @Rule val detailActivityTestRule: ActivityTestRule<DetailActivity> = ActivityTestRule(DetailActivity::class.java)
    private lateinit var detailActivity: DetailActivity


    @Before
    fun setUp() {
        mainActivityTestRule.launchActivity(Intent())
        mainActivity = mainActivityTestRule.activity

        trace("victor - DetailActivityTest - setUp!")
    }

    @After
    fun tearDown() {
        detailActivityTestRule.finishActivity()
    }



    // ---------------------------------------------------------------------------------------------
    // --------------------------------------------- TEST CASUISTIC --------------------------------
    @Given("^I have a MainActivity")
    fun I_have_a_MainActivity() {
        assertNotNull(mainActivity)
    }

    @When("^I click on weather forecast button$")
    fun I_click_on_weather_forecast_button() {
//        onView(withId(R.id.btnForecast)).perform(click())
    }

    @Then("^I should see weather forecast actvity")
    fun I_should_see_weather_forecast_actvity() {
//        detailActivityTestRule.launchActivity(Intent())
//        detailActivity = detailActivityTestRule.activity
//        val intent = Intent(mainActivity, detailActivity.javaClass)

//        trace("victor - I_should_see_weather_forecast_actvity!")
//        detailActivityTestRule.launchActivity(intent)

        onView(withId(R.id.btnForecast)).perform(click())
    }
}