package `in`.droidcon.info.common.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.info.common.model.EventEntity
import `in`.droidcon.info.event.domain.GetAllEventDetails
import `in`.droidcon.info.team.domain.GetAllTeamMembers
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * Created by Hari on 2019-08-16.
 * Shared view model for all pager fragments
 */
class InfoViewModel(
    private val getEventDetails: GetAllEventDetails,
    private val getTeam: GetAllTeamMembers
) : BaseViewModel() {

    private val eventDetailsState = MutableLiveData<ResultState<List<EventEntity>, String>>()

    private val teamListState = MutableLiveData<ResultState<List<GridItem>, String>>()

    fun getTeamListState(): LiveData<ResultState<List<GridItem>, String>> = teamListState

    fun getEventListState(): LiveData<ResultState<List<EventEntity>, String>> = eventDetailsState

    init {
        getEventDetails()
        getTeamList()
    }

    private fun getEventDetails() {
        getEventDetails.execute()
            .doOnSubscribe {
                eventDetailsState.postValue(ResultState.Loading)
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                eventDetailsState.postValue(ResultState.Success(list))
                Timber.i("Firestore fetching event details successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
            })
            .addTo(disposables)
    }

    private fun getTeamList() {
        getTeam.execute()
            .doOnSubscribe {
                teamListState.postValue(ResultState.Loading)
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                teamListState.postValue(ResultState.Success(list))
                Timber.i("Firestore fetching team successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                teamListState.postValue(ResultState.Failed(errorMessage))
                Timber.i("Firestore fetching team failed")
            })
            .addTo(disposables)
    }
}