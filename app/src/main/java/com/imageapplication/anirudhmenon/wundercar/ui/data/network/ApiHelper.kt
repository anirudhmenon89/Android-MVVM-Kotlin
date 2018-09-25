package com.imageapplication.anirudhmenon.wundercar.ui.data.network

import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails
import io.reactivex.Observable
import retrofit2.Call
import java.util.*

interface ApiHelper {
    fun getCarDetails(): Observable<CarDetails>
}