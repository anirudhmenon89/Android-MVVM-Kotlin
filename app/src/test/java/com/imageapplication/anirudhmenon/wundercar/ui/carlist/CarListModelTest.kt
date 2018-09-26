package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import com.imageapplication.anirudhmenon.wundercar.utils.TestSchedulerProvider
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CarListModelTest {
    @Mock
    lateinit var carListNavigator: CarListNavigator

    private var carListViewModel: CarListViewModel? = null

    private var testScheduler: TestScheduler? = null


    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler!!)
        carListViewModel = CarListViewModel(testSchedulerProvider)
        carListViewModel!!.setNavigator(carListNavigator)
    }

    @After
    fun tearDown() {
        carListViewModel = null
        testScheduler = null
    }

    /**
     * Check if variables are set correctly when API call is made
     */
    @Test
    fun checkCarListApiCall() {

        // Show progress bar at start
        assert(carListViewModel!!.isLoading.get())

        // Makse async api call and wait
        carListViewModel!!.getListOfCars()
        testScheduler!!.triggerActions()

        // isLoading should be false
        assert(!carListViewModel!!.isLoading.get())

        if (carListViewModel!!.isError.get()) {
            assert(carListViewModel!!.carDetails.size > 0)
        } else {
            assert(carListViewModel!!.carDetails.size == 0)
        }
    }
}