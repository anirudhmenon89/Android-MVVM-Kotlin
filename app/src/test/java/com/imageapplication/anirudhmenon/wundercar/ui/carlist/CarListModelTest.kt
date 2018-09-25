package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo
import com.imageapplication.anirudhmenon.wundercar.ui.data.network.WunderApiHelper
import com.imageapplication.anirudhmenon.wundercar.utils.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.mockito.Mockito
import org.mockito.Mockito.verify
import javax.inject.Inject


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

    @Test
    fun checkCarListApiCall() {

        carListViewModel!!.getListOfCars()
        testScheduler!!.triggerActions()

        if (carListViewModel!!.carDetails.size > 0) {
            assert(carListViewModel!!.isError.get())
        } else {
            assert(!carListViewModel!!.isError.get())
        }
    }
}