package `in`.droidcon.speakers.presentation.viewmodels

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.speakers.domain.GetAllSpeakers
import `in`.droidcon.base.state.TaskState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class SpeakerListViewModel(private val getSpeakers: GetAllSpeakers): BaseViewModel() {

    private val speakersListState = MutableLiveData<Event<TaskState>>()

    fun getSpeakersListState(): LiveData<Event<TaskState>> = speakersListState

    init {
        getSpeakersList()
    }

    private fun getSpeakersList() {
        getSpeakers.execute()
            .doOnSubscribe {
                speakersListState.postValue(Event(TaskState.Loading))
                Timber.i("API loading")
            }
            .subscribe({ list ->
                speakersListState.postValue(Event(TaskState.Success(list)))
                Timber.i("API fetching speakers successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                speakersListState.postValue(Event(TaskState.Failed(errorMessage)))
                Timber.i("API fetching speakers failed")
            })
            .addTo(disposables)
    }
}