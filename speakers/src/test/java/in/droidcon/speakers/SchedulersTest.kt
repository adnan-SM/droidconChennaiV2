package `in`.droidcon.speakers

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins

object SchedulersTest {
    fun test(testScheduler: Scheduler) {
        RxJavaPlugins.setInitIoSchedulerHandler { testScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }
    }
}