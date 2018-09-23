package com.imageapplication.anirudhmenon.wundercar.ui.data.network

import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails
import io.reactivex.Observable
import retrofit2.Call

class WunderApiHelper: ApiHelper {

    override fun getCarDetails(): Call<CarDetails> {
        val wunderNetworkService = WunderNetworkService.wunderHttp
                                .create(WunderNetworkService::class.java)
        return wunderNetworkService.getCarDetails()
    }
}