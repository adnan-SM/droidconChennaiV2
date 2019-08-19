package `in`.droidcon.speakers.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.speakers.domain.GetAllSpeakers
import `in`.droidcon.base.state.ResultState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class SpeakerListViewModel(private val getSpeakers: GetAllSpeakers): BaseViewModel() {

    private val speakersListState = MutableLiveData<Event<ResultState<List<GridItem>, String>>>()

    fun getSpeakersListState(): LiveData<Event<ResultState<List<GridItem>, String>>> = speakersListState

    init {
        getSpeakersList()
    }

    private fun getSpeakersList() {
        getSpeakers.execute()
            .doOnSubscribe {
                speakersListState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                speakersListState.postValue(Event(ResultState.Success(list)))
                Timber.i("Firestore fetching speakers successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                speakersListState.postValue(Event(ResultState.Failed(errorMessage)))
                Timber.i("Firestore fetching speakers failed")
            })
            .addTo(disposables)
    }
}