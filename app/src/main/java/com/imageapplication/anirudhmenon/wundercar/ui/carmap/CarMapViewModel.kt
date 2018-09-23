package com.imageapplication.anirudhmenon.wundercar.ui.carmap

import com.imageapplication.anirudhmenon.wundercar.ui.WunderApplication
import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CarMapViewModel: BaseViewModel<CarMapNavigator>() {


    fun parseMarkers() {
        Observable.just(WunderApplication.getInstance().carDetails.placemarks)
                .flatMapIterable { list -> list }
                .map { list -> list.coordinates }
                .map { coords -> CoordinateData(coords[1], coords[0])}
                .toList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({data -> navigator.get()!!.renderMarkerOnMap(data)})
    }

}