package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.databinding.ObservableArrayList
import android.util.Log
import com.imageapplication.anirudhmenon.wundercar.ui.WunderApplication
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseNavigator
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseViewModel
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo
import com.imageapplication.anirudhmenon.wundercar.ui.data.network.WunderApiHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarListViewModel : BaseViewModel<CarListNavigator>() {

    var carDetails = ObservableArrayList<CarInfo>()

    fun getListOfCars() {
        setIsLoading(true)

        wunderApiHelper.getCarDetails().enqueue(object: Callback<CarDetails>{
            override fun onFailure(call: Call<CarDetails>?, t: Throwable?) {
                setIsLoading(false)
                showErrorScreen(true)
            }

            override fun onResponse(call: Call<CarDetails>?, response: Response<CarDetails>?) {
                setIsLoading(false)
                setCarDetails(response?.body())
                if (response != null) {
                    WunderApplication.getInstance().carDetails = response.body()!!.copy()
                }
            }
        })
    }

    private fun setCarDetails(carDetail: CarDetails?) {
        carDetails.clear()
        carDetails.addAll(carDetail!!.placemarks)
    }

}

