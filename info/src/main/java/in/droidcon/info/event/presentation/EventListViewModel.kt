package `in`.droidcon.info.event.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.event.Event
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.info.event.domain.GetAllEventDetails
import `in`.droidcon.info.common.model.EventEntity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * Created by Hari on 2019-08-14.
 * View model for event details
 */
class EventListViewModel(private val getEventDetails: GetAllEventDetails): BaseViewModel() {

    private val eventDetailsState = MutableLiveData<Event<ResultState<List<EventEntity>, String>>>()

    fun getEventListState(): LiveData<Event<ResultState<List<EventEntity>, String>>> = eventDetailsState

    init {
        getEventDetails()
    }

    private fun getEventDetails() {
        getEventDetails.execute()
            .doOnSubscribe {
                eventDetailsState.postValue(Event(ResultState.Loading))
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                eventDetailsState.postValue(Event(ResultState.Success(list)))
                Timber.i("Firestore fetching event details successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
            })
            .addTo(disposables)
    }
}