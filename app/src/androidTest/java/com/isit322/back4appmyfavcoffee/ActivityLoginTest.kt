package com.isit322.back4appmyfavcoffee

import android.app.Activity
import android.content.ComponentName
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.isit322.back4appmyfavcoffee.ui.login.LoginActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent as hasComponent


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ActivityLoginTest {
    @Rule
    @JvmField
    val rule: ActivityScenarioRule<LoginActivity> = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.isit322.back4appmyfavcoffee", appContext.packageName)
    }

    @Test
    fun LoginTitleRender_ShouldBePresent() {
        onView(withId(R.id.tvTitle)).check(matches(withText("Join the community")))
    }
    @Test
    fun myFavCoffeeLogo_ShouldBePresent() {
        onView(withId(R.id.imageView)).check(matches(withContentDescription("myFavCoffee Logo")))
    }
    @Test
    fun LoginParagraphRender_ShouldBePresent() {
    onView(withId(R.id.textView2)).check(matches(withText("Let's get to know all the good coffee shops on the market and follow the ones we like the most! You don’t want to lose the opportunity to buy a good coffee.")))
    }

    @Test
    fun Login_ShouldLaunchUserToPrefQuiz() {
        onView(withId(R.id.username)).perform(ViewActions.typeText("UserName"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.password)).perform(ViewActions.typeText("password"),ViewActions.closeSoftKeyboard())
        onView(withId(R.id.login)).perform(click())
        onView(withId(R.id.coffeeGroup)).check(matches(ViewMatchers.isDisplayed()))

    }

}