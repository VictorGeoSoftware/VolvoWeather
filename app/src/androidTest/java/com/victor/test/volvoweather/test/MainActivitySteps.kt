package com.victor.test.volvoweather.test

import android.app.Activity
import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.victor.test.volvoweather.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule

class MainActivitySteps {
    @Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    lateinit var activity:Activity

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
        activity = activityTestRule.activity
    }

    @After
    fun tearDown() {
        activityTestRule.finishActivity()
    }



    /*
    @When("^I input an email, test@test\.com")
public void I_input_an_email() {
    onView(withId(R.id.email)).perform(typeText("test@test.com");
}

...

@Then("^I should not see auth error")
public void I_should_not_see_auth_error() {
    onView(withId(R.id.error)).check(matches(not(isDisplayed())));
}
     */




}