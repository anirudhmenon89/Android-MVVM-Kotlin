package com.imageapplication.anirudhmenon.wundercar

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.CarListActivity
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.CarListViewModel
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview.CarListAdapter
import com.imageapplication.anirudhmenon.wundercar.ui.carmap.CarMapActivity
import com.imageapplication.anirudhmenon.wundercar.utils.UiTestUtils
import com.imageapplication.anirudhmenon.wundercar.utils.UiTestUtils.Companion.withIndex
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import java.util.*


@RunWith(AndroidJUnit4::class)
class CarListActivityTest {

    @get:Rule
    public var activity = ActivityTestRule(CarListActivity::class.java)

    var mapActivityMonitor = InstrumentationRegistry
            .getInstrumentation()
            .addMonitor(CarMapActivity::class.java.name, null, false)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearAway() {
        Intents.release()
    }

    @Test
    fun checkViewsOnDisplay() {
        if (activity.activity.carListViewModel.isLoading.get()) {
            Espresso
                    .onView(withId(R.id.progress_bar))
                    .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

            Espresso.onView(withId(R.id.rv_car_list))
                    .check(matches(withEffectiveVisibility(Visibility.GONE)))
        } else {
            Espresso
                    .onView(withId(R.id.progress_bar))
                    .check(matches(withEffectiveVisibility(Visibility.GONE)))

            Espresso.onView(withId(R.id.rv_car_list))
                    .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        }
    }

    @Test
    fun recyclerViewScroll() {
        Espresso.onView(withId(R.id.rv_car_list))
                .perform(RecyclerViewActions.scrollToPosition<CarListAdapter.CarListHolder>(selectRandomNumberInList()))
    }

    @Test
    fun recyclerViewClick() {
        Espresso.onView(withId(R.id.rv_car_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<CarListAdapter.CarListHolder>(selectRandomNumberInList(), click()))
        Intents.intended(IntentMatchers.hasComponent(CarMapActivity::class.java.name))
    }

    private fun selectRandomNumberInList(): Int {
        return (0..activity.activity.carListViewModel.carDetails.size).shuffled().first()
    }
}