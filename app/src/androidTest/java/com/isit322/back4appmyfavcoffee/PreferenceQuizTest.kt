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
import java.util.regex.Matcher

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

class PreferenceQuizTest {
    @Rule
    @JvmField

    val rule: ActivityScenarioRule<PreferenceQuizActivity> = ActivityScenarioRule(PreferenceQuizActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.isit322.back4appmyfavcoffee", appContext.packageName)
    }

    @Test
    fun a_PreferenceQuizRBG1_WhiteChocolateShouldBeSelected() {
        //setup
        val premiumCoffeeArray = arrayOf<Int>(R.id.NitroColdBrew, R.id.CaramelVanilla, R.id.Cappuccino, R.id.Frappuccino)
        //Perform Click
        onView(withId(R.id.WhiteChocolate)).perform(click())
        //Check Espresso is clicked and no other other buttons are clicked
        onView(withId(R.id.WhiteChocolate))
            .check(matches(isChecked()))
        for (i in premiumCoffeeArray.indices) {
            onView(withId(premiumCoffeeArray[i]))
                .check(matches(isNotChecked()))
        }
    }

    @Test
    fun b_PreferenceQuizRBG2_EspressoShouldBeSelected() {
        //setup
        val coffeeArray = arrayOf<Int>(R.id.Latte, R.id.IcedCoffee)
        //Perform Click
        onView(withId(R.id.Espresso)).perform(click())
        //Check Espresso is clicked and no other other buttons are clicked
        onView(withId(R.id.Espresso))
            .check(matches(isChecked()))
        for (i in coffeeArray.indices) {
            onView(withId(coffeeArray[i]))
                .check(matches(isNotChecked()))
        }
    }



    @Test
    fun c_PreferenceQuizRBG3_SandwichShouldBeSelected() {
        //prepare
        val foodArray = arrayOf<Int>(R.id.Bagels, R.id.donuts, R.id.croissant, R.id.cupcakes)
        //Perform Click on sandwich RadioButton Item
        onView(withId(R.id.sandwich)).perform(click())
        //Check sandwich is clicked
        onView(withId(R.id.sandwich))
            .check(matches(isChecked()))

        // iterate to ensure no other buttons are selected
        for (i in foodArray.indices) {
            onView(withId(foodArray[i]))
                .check(matches(isNotChecked()))
        }

    }
    @Test
    fun d_PreferenceQuizSubmission_ShouldDirectUserToShopActivity() {
        //
        val shouldNotBeSelectedArray = arrayOf<Int>(R.id.Bagels, R.id.donuts, R.id.croissant, R.id.cupcakes, R.id.NitroColdBrew, R.id.CaramelVanilla, R.id.Cappuccino, R.id.Frappuccino)
        val shouldBeSelectedArray = arrayOf<Int>(R.id.sandwich, R.id.Espresso, R.id.WhiteChocolate)
        //Iterate through clicks
        for (i in shouldBeSelectedArray.indices) {
            onView(withId(shouldBeSelectedArray[i])).perform(click())
        }
        // iterate to ensure buttons are  selected
        for (i in shouldBeSelectedArray.indices) {
            onView(withId(shouldBeSelectedArray[i]))
                .check(matches(isChecked()))
        }
        // iterate to ensure buttons are NOT selected
        for (i in shouldNotBeSelectedArray.indices) {
            onView(withId(shouldNotBeSelectedArray[i]))
                .check(matches(isNotChecked()))
        }
        //Click Form Submit
        onView(withId(R.id.btnSubmit))
           .perform(scrollTo())
            .perform(click())
        //Check for activity_main element.
        // **** Helper functions wait on element to load.
        //onView(isRoot()).perform(waitForView(R.id.test, 5000))
        Thread.sleep(5000)
        onView(withId(R.id.btn_map_activity))
            .check(matches(isDisplayed()))
    }







    // CustomViewActions.kt:
    /**
     * This ViewAction tells espresso to wait till a certain view is found in the view hierarchy.
     * @param viewId The id of the view to wait for.
     * @param timeout The maximum time which espresso will wait for the view to show up (in milliseconds)
     */
    fun waitForView(viewId: Int, timeout: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): org.hamcrest.Matcher<View>? {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for a specific view with id $viewId; during $timeout millis."
            }

            override fun perform(uiController: UiController, rootView: View) {
                uiController.loopMainThreadUntilIdle()
                val startTime = System.currentTimeMillis()
                val endTime = startTime + timeout
                val viewMatcher = withId(viewId)

                do {
                    // Iterate through all views on the screen and see if the view we are looking for is there already
                    for (child in TreeIterables.breadthFirstViewTraversal(rootView)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return
                        }
                    }
                    // Loops the main thread for a specified period of time.
                    // Control may not return immediately, instead it'll return after the provided delay has passed and the queue is in an idle state again.
                    uiController.loopMainThreadForAtLeast(100)
                } while (System.currentTimeMillis() < endTime) // in case of a timeout we throw an exception -> test fails
                throw PerformException.Builder()
                    .withCause(TimeoutException())
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(rootView))
                    .build()
            }
        }
    }

}



