package com.imageapplication.anirudhmenon.wundercar.ui.carmap

import com.imageapplication.anirudhmenon.wundercar.ui.WunderApplication
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CarMapViewModel : BaseViewModel<CarMapNavigator>() {


    fun parseMarkers() {
        Observable.just(WunderApplication.getInstance().carDetails.placemarks)
                .flatMapIterable { list -> list }
                .map { list -> CarInfoForMap(list.coordinates, list.name) }
                .map { dataInfo -> CoordinateData(dataInfo.coords[1], dataInfo.coords[0], dataInfo.name) }
                .toList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data -> navigator.get()!!.renderMarkerOnMap(data) }
    }

    /**
     * Data class that holds the complex information about latitude, longitude and car name
     */
    data class CarInfoForMap(val coords: ArrayList<Double>, val name: String)

}