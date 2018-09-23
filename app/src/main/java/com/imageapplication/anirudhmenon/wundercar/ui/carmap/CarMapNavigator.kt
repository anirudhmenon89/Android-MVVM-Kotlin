package com.imageapplication.anirudhmenon.wundercar.ui.carmap

import com.imageapplication.anirudhmenon.wundercar.ui.base.BaseNavigator

interface CarMapNavigator: BaseNavigator {
    fun renderMarkerOnMap(list: MutableList<CoordinateData>)
}