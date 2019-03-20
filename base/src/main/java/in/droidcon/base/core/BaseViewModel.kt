package `in`.droidcon.base.core

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    companion object {
        const val ERROR_MESSAGE = "Something went wrong. Please try again"
    }
}
