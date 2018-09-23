package com.imageapplication.anirudhmenon.wundercar.ui.data.model.api

data class CarDetails(
        val placemarks: ArrayList<CarInfo>
)

data class CarInfo(
        val address: String,
        val engineType: String,
        val exterior: String,
        val fuel: Int,
        val interior: String,
        val name: String,
        val vin: String,
        val coordinates: ArrayList<Double>)





