package `in`.droidcon.speakers.di


import `in`.droidcon.speakers.presentation.ui.SpeakersAdapter
import `in`.droidcon.speakers.presentation.viewmodels.SpeakerListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val speakersModule = module {

//    factory<SpeakerFirestore> { SpeakerFirestoreImpl() }
//
//    factory<SpeakerRepository> { SpeakerDataRepository(speakerFirestore = get()) }
//
//    factory { GetAllSpeakers(executionThread = get(), speakerRepository = get()) }
//    factory { GetOneSpeaker(executionThread = get(), speakerRepository = get()) }
//

//    viewModel { SpeakerDetailViewModel(getOneSpeaker = get()) }
//
//    factory { (context: SpeakerDetailController.SpeakerDetailCallbacks) -> SpeakerDetailController(context) }

    factory { (context: SpeakersAdapter.ListItemClickListener) -> SpeakersAdapter(context) }
}


val viewModelModule = module {

    viewModel { SpeakerListViewModel(getSpeakers = get()) }

}