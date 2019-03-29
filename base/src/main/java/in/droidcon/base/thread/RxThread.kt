package `in`.droidcon.base.thread

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RxThread : ExecutionThread {

    override val ioThread: Scheduler = Schedulers.io()

    override val mainThread: Scheduler = AndroidSchedulers.mainThread()

}