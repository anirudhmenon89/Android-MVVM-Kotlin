package com.imageapplication.anirudhmenon.wundercar.ui.data.network

import com.imageapplication.anirudhmenon.wundercar.BuildConfig
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface WunderNetworkService {

    @GET(NetworkEndPoint.CAR_LOCATION_LIST)
    fun getCarDetails(): Observable<CarDetails>


    companion object {
        private var retrofit: Retrofit? = null

        val wunderHttp: Retrofit get() {
                if (retrofit == null) {
                    val builder = OkHttpClient.Builder()

                    val okHttpClient = getOkHttpClient()

                    retrofit = Retrofit.Builder()
                            .baseUrl(BuildConfig.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHttpClient)
                            .build()
                }

                return retrofit!!
            }

        fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();
        }
    }
}