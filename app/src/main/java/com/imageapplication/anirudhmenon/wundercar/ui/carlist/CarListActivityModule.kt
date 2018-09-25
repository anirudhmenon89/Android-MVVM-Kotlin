package com.imageapplication.anirudhmenon.wundercar.ui.carlist

import com.imageapplication.anirudhmenon.wundercar.ui.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class CarListActivityModule {

    @Provides
    fun provideCarListViewModel(schedulerProvider: SchedulerProvider): CarListViewModel {
        return CarListViewModel(schedulerProvider)
    }
}