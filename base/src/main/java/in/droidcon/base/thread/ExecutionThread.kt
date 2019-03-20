package `in`.droidcon.speakers

import io.reactivex.Scheduler

interface ExecutionThread {

    val ioThread: Scheduler

    val mainThread: Scheduler
}
