package com.imageapplication.anirudhmenon.wundercar.ui.di.component

import android.app.Application
import com.imageapplication.anirudhmenon.wundercar.ui.WunderApplication
import com.imageapplication.anirudhmenon.wundercar.ui.di.builder.ActivityBuilder
import com.imageapplication.anirudhmenon.wundercar.ui.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class, AppModule::class])
interface AppComponent {
    fun inject(app: WunderApplication)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}