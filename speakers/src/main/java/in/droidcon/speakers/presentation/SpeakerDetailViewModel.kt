package `in`.droidcon.speakers.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.speakers.domain.GetOneSpeaker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * Created by Hari on 2019-07-23.
 * View model for speaker detail
 */
class SpeakerDetailViewModel(private val getOneSpeaker: GetOneSpeaker): BaseViewModel() {

    private val speakerState = MutableLiveData<Event<ResultState<GridItem, String>>>()

    fun getSpeakerState(): LiveData<Event<ResultState<GridItem, String>>> = speakerState

    fun getOneSpeaker(id: String) {
        getOneSpeaker.execute(GetOneSpeaker.Companion.Params(id))
            .doOnSubscribe {
                speakerState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ speaker ->
                speakerState.postValue(Event(ResultState.Success(speaker)))
                Timber.i("Firestore fetching speaker successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                speakerState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching speaker failed")
            })
            .addTo(disposables)
    }
}