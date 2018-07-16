package com.victor.test.volvoweather

import android.app.Activity
import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.victor.test.volvoweather.ui.main.MainActivity
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.After
import org.junit.Assert
import org.junit.Before
import java.util.regex.Matcher

/**
 * Created by victorpalmacarrasco on 11/7/18.
 * ${APP_NAME}
 */
class MainActivitySteps {
    private val activityTestRule:ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    lateinit var activity:Activity



    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------- TEST SET UP ----------------------------------------
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
    // ---------------------------------------- TEST CASES -----------------------------------------
    @Given("^I have a MainActivity")
    fun I_have_a_MainActivity() {
        Assert.assertNotNull(activity)
    }

    @When("^activity is created")
    fun Activity_is_created() {

    }

    @Then("^I should see current weather")
    fun I_should_see_current_weather() {
        onView(withId(R.id.txtWeather)).check(matches(withText("28º")))
    }


    /*
    @When("^I input email (\\S+)$")
    public void I_input_email(final String email) {
        onView(withId(R.id.email)).perform(typeText(email));
    }

    @When("^I input password \"(.*?)\"$")
    public void I_input_password(final String password) {
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
    }

    @When("^I press submit button$")
    public void I_press_submit_button() {
        onView(withId(R.id.submit)).perform(click());
    }

    @Then("^I should see error on the (\\S+)$")
    public void I_should_see_error_on_the_editTextView(final String viewName) {
        int viewId = (viewName.equals("email")) ? R.id.email : R.id.password;
        int messageId = (viewName.equals("email")) ? R.string.msg_email_error : R.string.msg_password_error;

        onView(withId(viewId)).check(matches(hasErrorText(activity.getString(messageId))));
    }
     */
}