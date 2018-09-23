package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import android.databinding.ObservableField
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo

class CarListItemViewModel {
    var address: ObservableField<String>
    var name: ObservableField<String>
    var carInfo: CarInfo
    var clickListener: CarListItemClick

    constructor(carInfo: CarInfo, clickListener: CarListItemClick) {
        this.carInfo = carInfo

        address = ObservableField(carInfo.address)
        name = ObservableField(carInfo.name)

        this.clickListener = clickListener

    }

    fun onItemClick() {
        clickListener.onItemClick()
    }

    interface CarListItemClick {
        fun onItemClick()
    }
}