package com.imageapplication.anirudhmenon.wundercar.ui

import android.app.Application
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails

class WunderApplication: Application() {

    lateinit var carDetails: CarDetails

    companion object {

        lateinit var appInstance: WunderApplication
        @JvmStatic
        fun getInstance(): WunderApplication {
            return appInstance
        }
    }


    fun setGlobalCarDetails(carDetails: CarDetails) {
        this.carDetails = carDetails
    }

    override fun onCreate() {
        super.onCreate()

        appInstance = this
    }




}