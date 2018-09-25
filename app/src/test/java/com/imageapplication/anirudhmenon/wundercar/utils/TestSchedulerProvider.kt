package com.imageapplication.anirudhmenon.wundercar.utils

import com.imageapplication.anirudhmenon.wundercar.ui.utils.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(private var testScheduler: TestScheduler) : SchedulerProvider {

    override fun computation(): Scheduler {
        return testScheduler
    }

    override fun io(): Scheduler {
        return testScheduler
    }

    override fun ui(): Scheduler {
        return testScheduler
    }
}