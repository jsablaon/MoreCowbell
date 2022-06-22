package com.isit322.back4appmyfavcoffee

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters
import java.util.concurrent.TimeoutException

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {


        @Rule
        @JvmField
        //We still have to start at the preference Activity as the menu is dependent on pref choices
        val rule: ActivityScenarioRule<PreferenceQuizActivity> = ActivityScenarioRule(PreferenceQuizActivity::class.java)

        @Test
        fun a_MainActivity_ShopNamesShouldBePresent() {

            //Setup
            val shouldBeSelectedArray = arrayOf<Int>(R.id.sandwich, R.id.Espresso, R.id.WhiteChocolate)
                //Select Items
            for (i in shouldBeSelectedArray.indices) {
                onView(withId(shouldBeSelectedArray[i]))
                    .perform(click())
            }
                //Submit Menu
            onView(withId(R.id.btnSubmit))
                .perform(scrollTo())
                .perform(click())
            Thread.sleep(5000)
        //Check CoffeeShops are Present
            onView(withId(R.id.fav_1_name)).check(matches(withText("Capitol Coffee Works")))
            onView(withId(R.id.fav_2_name)).check(matches(withText("Cascade Coffee Works")))
            onView(withId(R.id.fav_3_name)).check(matches(withText("Pegasus Coffee Bar")))
        }

        @Test
        fun b_MainActivity_LoginNameShouldBePresent() {

            //Setup
            val shouldBeSelectedArray = arrayOf<Int>(R.id.sandwich, R.id.Espresso, R.id.WhiteChocolate)
                 //Select Items
            for (i in shouldBeSelectedArray.indices) {
                onView(withId(shouldBeSelectedArray[i]))
                    .perform(click())
            }
                 //Submit Menu
            onView(withId(R.id.btnSubmit))
                .perform(scrollTo())
                .perform(click())
            Thread.sleep(3000)
            //Check CoffeeShops are Present
            onView(withId(R.id.welcome_user)).check(matches(withText("Welcome, Johnny")))
        }


}