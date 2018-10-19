package com.imageapplication.anirudhmenon.wundercar.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.imageapplication.anirudhmenon.wundercar.ui.data.network.WunderApiHelper
import java.lang.ref.WeakReference

abstract class BaseViewModel<N : BaseNavigator>() : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Boolean>()
    lateinit var navigator: WeakReference<N>

    protected var wunderApiHelper = WunderApiHelper()

    init {
        isLoading.value = true
        isError.value = false
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.postValue(isLoading)
    }

    fun showErrorScreen(showError: Boolean) {
        this.isError.postValue(showError)
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }


}