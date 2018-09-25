package com.imageapplication.anirudhmenon.wundercar.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.imageapplication.anirudhmenon.wundercar.ui.data.network.WunderApiHelper
import java.lang.ref.WeakReference

abstract class BaseViewModel<N : BaseNavigator>() : ViewModel() {
    var isLoading = ObservableBoolean(true)
    var isError = ObservableBoolean(false)
    lateinit var navigator: WeakReference<N>

    protected var wunderApiHelper = WunderApiHelper()

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.set(isLoading)
    }

    fun showErrorScreen(showError: Boolean) {
        this.isError.set(showError)
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }


}