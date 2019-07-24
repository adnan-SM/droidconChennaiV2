package `in`.droidcon.base.thread

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by Hari on 2019-07-24.
 * For test. Use only for mocking in unit tests
 */
class TestThread : ExecutionThread {

    override val ioThread: Scheduler = Schedulers.trampoline()

    override val mainThread: Scheduler = Schedulers.trampoline()
}