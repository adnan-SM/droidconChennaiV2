package `in`.droidcon.speakers.di

import `in`.droidcon.base.repo.remote.ServiceFactory
import `in`.droidcon.speakers.BuildConfig
import `in`.droidcon.speakers.domain.GetAllSpeakers
import `in`.droidcon.speakers.domain.SpeakerRepository
import `in`.droidcon.speakers.domain.SpeakerRepositoryImplementation
import `in`.droidcon.speakers.presentation.ui.SpeakersAdapter
import `in`.droidcon.speakers.presentation.viewmodels.SpeakerListViewModel
import `in`.droidcon.speakers.remote.SpeakerService
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val speakersModule = module(override=true) {

    factory { SpeakerRepositoryImplementation(get(), get()) }
    factory<SpeakerRepository> { SpeakerRepositoryImplementation(get(), get()) }
    factory { GetAllSpeakers(executionThread = get(), speakerRepository = get()) }
//    factory { GetOneSpeaker(executionThread = get(), speakerRepository = get()) }
//    viewModel { SpeakerDetailViewModel(getOneSpeaker = get()) }
//    factory { (context: SpeakerDetailController.SpeakerDetailCallbacks) -> SpeakerDetailController(context) }

    factory { (context: SpeakersAdapter.ListItemClickListener) -> SpeakersAdapter(context) }

    factory { ServiceFactory.makeService<SpeakerService>(BuildConfig.DEBUG) }
}


val viewModelModule = module {

    viewModel { SpeakerListViewModel(getSpeakers = get()) }

}