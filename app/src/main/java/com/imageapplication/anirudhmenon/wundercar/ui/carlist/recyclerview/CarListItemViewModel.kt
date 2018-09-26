package com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.view.View
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo

class CarListItemViewModel(var carInfo: CarInfo, var clickListener: CarListItemClick) {
    var address: ObservableField<String> = ObservableField(carInfo.address)
    var interior: ObservableField<String> = ObservableField(carInfo.interior)
    var exterior: ObservableField<String> = ObservableField(carInfo.exterior)

    val coordinates: ArrayList<Double>
        get() {
            val coords = ObservableArrayList<Double>()
            coords.addAll(carInfo.coordinates)
            return coords
        }


    fun onItemClick(view: View, viewModel: CarListItemViewModel) {
        clickListener.onItemClick(view, viewModel)
    }

    interface CarListItemClick {
        fun onItemClick(view: View, viewModel: CarListItemViewModel)
    }
}