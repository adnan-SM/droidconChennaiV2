package `in`.droidcon.base.core

import `in`.droidcon.base.thread.ExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasicSingleUseCase<T> constructor(
        private val executionThread: ExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCase(): Single<T>

    open fun execute(): Single<T> {
        return this.buildUseCase()
                .subscribeOn(executionThread.ioThread)
                .observeOn(executionThread.mainThread)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}