package com.imageapplication.anirudhmenon.wundercar.ui.carlist.recyclerview

import android.databinding.ObservableField
import android.view.View
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarInfo

class CarListItemViewModel(carInfo: CarInfo, var clickListener: CarListItemClick) {
    var address: ObservableField<String> = ObservableField(carInfo.address)
    var name: ObservableField<String> = ObservableField(carInfo.name)

    fun onItemClick(view: View, viewModel: CarListItemViewModel) {
        clickListener.onItemClick(view, viewModel)
    }

    interface CarListItemClick {
        fun onItemClick(view: View, viewModel: CarListItemViewModel)
    }
}