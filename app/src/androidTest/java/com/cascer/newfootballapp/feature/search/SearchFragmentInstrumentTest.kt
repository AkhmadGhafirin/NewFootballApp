package com.cascer.newfootballapp.feature.search

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.cascer.newfootballapp.R
import com.cascer.newfootballapp.feature.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentInstrumentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityRule.activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment()).commit()
    }

    @Test
    fun search() {
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.fragment_search_parent)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.et_search_match)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.et_search_match))
            .perform(ViewActions.typeText("liverpool"))
        pressImeActionButton()
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.rv_search_result)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.rv_search_result)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                ViewActions.click()
            )
        )
        pressBack()
    }
}