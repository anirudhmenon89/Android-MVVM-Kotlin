package com.imageapplication.anirudhmenon.wundercar.ui.di.builder

import com.imageapplication.anirudhmenon.wundercar.ui.carlist.CarListActivity
import com.imageapplication.anirudhmenon.wundercar.ui.carlist.CarListActivityModule
import com.imageapplication.anirudhmenon.wundercar.ui.carmap.CarMapActivity
import com.imageapplication.anirudhmenon.wundercar.ui.carmap.CarMapActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [CarListActivityModule::class])
    abstract fun bindCarListActivity(): CarListActivity

    @ContributesAndroidInjector(modules = [CarMapActivityModule::class])
    abstract fun bindCarMapActivity(): CarMapActivity
}