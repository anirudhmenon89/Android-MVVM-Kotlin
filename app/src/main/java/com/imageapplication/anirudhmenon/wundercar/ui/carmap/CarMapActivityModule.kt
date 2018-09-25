package com.imageapplication.anirudhmenon.wundercar.ui.carmap

import dagger.Module
import dagger.Provides

@Module
class CarMapActivityModule {
    @Provides
    fun provideCarMapActivityViewModel(): CarMapViewModel {
        return CarMapViewModel()
    }
}