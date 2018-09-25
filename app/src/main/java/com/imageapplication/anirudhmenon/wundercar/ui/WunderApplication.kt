package com.imageapplication.anirudhmenon.wundercar.ui

import android.app.Activity
import android.app.Application
import com.imageapplication.anirudhmenon.wundercar.ui.data.model.api.CarDetails
import com.imageapplication.anirudhmenon.wundercar.ui.di.component.AppComponent
import com.imageapplication.anirudhmenon.wundercar.ui.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class WunderApplication: Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

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

        DaggerAppComponent.builder().application(this).build().inject(this)

        appInstance = this
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

}