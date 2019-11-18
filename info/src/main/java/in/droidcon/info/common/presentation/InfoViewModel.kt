package `in`.droidcon.info.common.presentation

import `in`.droidcon.base.core.BaseViewModel
import `in`.droidcon.base.model.GridItem
import `in`.droidcon.base.state.ResultState
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.event.domain.GetAllEventDetails
import `in`.droidcon.info.general.domain.GetAllGeneralDetails
import `in`.droidcon.info.sponsors.domain.GetAllSponsors
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
    private val getTeam: GetAllTeamMembers,
    private val getAllSponsors: GetAllSponsors,
    private val getAllGeneralDetails: GetAllGeneralDetails
) : BaseViewModel() {

    private val eventDetailsState = MutableLiveData<ResultState<List<InfoEntity>, String>>()
    private val generalDetailsState = MutableLiveData<ResultState<List<InfoEntity>, String>>()

    private val teamListState = MutableLiveData<ResultState<List<GridItem>, String>>()
    private val sponsorListState = MutableLiveData<ResultState<List<GridItem>, String>>()

    fun getTeamListState(): LiveData<ResultState<List<GridItem>, String>> = teamListState

    fun getSponsorListState(): LiveData<ResultState<List<GridItem>, String>> = sponsorListState

    fun getEventListState(): LiveData<ResultState<List<InfoEntity>, String>> = eventDetailsState

    fun getGeneralListState(): LiveData<ResultState<List<InfoEntity>, String>> = generalDetailsState

    init {
        getEventDetails()
        getSponsorList()
        getTeamList()
        getGeneralDetails()
    }

    private fun getGeneralDetails() {
        getAllGeneralDetails.execute()
            .doOnSubscribe {
                generalDetailsState.postValue(ResultState.Loading)
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                generalDetailsState.postValue(ResultState.Success(list))
                Timber.i("Firestore fetching general details successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                generalDetailsState.postValue(ResultState.Failed(errorMessage))
                Timber.i("Firestore fetching general details failed")
            })
            .addTo(disposables)
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
                eventDetailsState.postValue(ResultState.Failed(errorMessage))
                Timber.i("Firestore fetching event failed")
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

    private fun getSponsorList() {
        getAllSponsors.execute()
            .doOnSubscribe {
                sponsorListState.postValue(ResultState.Loading)
                Timber.i("Firestore loading")
            }
            .subscribe({ list ->
                sponsorListState.postValue(ResultState.Success(list))
                Timber.i("Firestore fetching sponsors successful")
            }, { throwable ->
                val errorMessage = throwable.message ?: ERROR_MESSAGE
                sponsorListState.postValue(ResultState.Failed(errorMessage))
                Timber.i("Firestore fetching sponsors failed")
            })
            .addTo(disposables)
    }
}