package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.databinding.ObservableArrayList
import com.imageapplication.anirudhmenon.wundercar.ui.WunderApplication
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseViewModel
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo
import com.imageapplication.anirudhmenon.wundercar.ui.utils.rx.SchedulerProvider

open class CarListViewModel(val schedulerProvider: SchedulerProvider) : BaseViewModel<CarListNavigator>() {

    var carDetails = ObservableArrayList<CarInfo>()

    fun getListOfCars() {
        setIsLoading(true)
        wunderApiHelper.getCarDetails()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ data -> loadSuccess(data) },
                        { error -> loadError() })
    }

    private fun setCarDetails(carDetail: CarDetails?) {
        carDetails.clear()
        carDetails.addAll(carDetail!!.placemarks)
    }

    private fun loadSuccess(carDetails: CarDetails) {
        setIsLoading(false)
        isError.postValue(false)
        setCarDetails(carDetails)
        WunderApplication.getInstance().carDetails = carDetails
    }

    private fun loadError() {
        setIsLoading(false)
        showErrorScreen(true)
        isError.postValue(true)
    }
}

