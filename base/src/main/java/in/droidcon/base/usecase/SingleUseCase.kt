package `in`.droidcon.base.usecase

import `in`.droidcon.base.thread.ExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Hari on 2019-07-23.
 * Single Usecase to create Single interactor
 */
abstract class SingleUseCase<T, in Params> constructor(
    private val postExecutionThread: ExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCase(params: Params): Single<T>

    open fun execute(params: Params): Single<T> {
        return buildUseCase(params)
            .subscribeOn(postExecutionThread.ioThread)
            .observeOn(postExecutionThread.mainThread)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}