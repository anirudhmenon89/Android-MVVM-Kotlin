package com.imageapplication.anirudhmenon.wundercar.ui.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelProviderFactory<V: Any>: ViewModelProvider.Factory {

    private var viewModel: V

    constructor(viewModel: V) {
        this.viewModel = viewModel
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)){
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name");
    }
}