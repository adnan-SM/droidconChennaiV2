package `in`.droidcon.base.thread

import io.reactivex.Scheduler

interface ExecutionThread {

    val ioThread: Scheduler

    val mainThread: Scheduler
}
