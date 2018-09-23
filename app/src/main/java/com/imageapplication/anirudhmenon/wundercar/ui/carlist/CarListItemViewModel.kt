package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.databinding.ObservableField
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo

class CarListItemViewModel {
    var address: ObservableField<String>? = null
    var name: ObservableField<String>? = null
    var carInfo: CarInfo? = null

    constructor(carInfo: CarInfo) {
        this.carInfo = carInfo
        address = ObservableField(carInfo.address)
        name = ObservableField(carInfo.name)

    }

    interface CarListItemClick {
        fun onItemClick(position: Int, carInfo: CarInfo)
    }
}