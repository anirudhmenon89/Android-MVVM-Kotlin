package com.imageapplication.anirudhmenon.wundercar.ui.di.module

import com.imageapplication.anirudhmenon.wundercar.ui.utils.rx.AppSchedulerProvider
import com.imageapplication.anirudhmenon.wundercar.ui.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}