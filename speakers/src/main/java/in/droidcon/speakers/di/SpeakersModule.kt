package `in`.droidcon.speakers.di

import `in`.droidcon.speakers.domain.GetAllSpeakers
import `in`.droidcon.speakers.domain.GetOneSpeaker
import `in`.droidcon.speakers.repository.SpeakerRepository
import `in`.droidcon.speakers.repository.SpeakerRepositoryImpl
import `in`.droidcon.speakers.presentation.SpeakerDetailViewModel
import `in`.droidcon.speakers.presentation.SpeakerListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val speakersModule = module {

    factory { SpeakerRepositoryImpl() }
    factory<SpeakerRepository> { SpeakerRepositoryImpl() }
    factory { GetAllSpeakers(executionThread = get(), speakerRepository = get()) }
    factory {
        GetOneSpeaker(
            executionThread = get(),
            speakerRepository = get()
        )
    }
    viewModel { SpeakerDetailViewModel(getOneSpeaker = get()) }
    viewModel { SpeakerListViewModel(getSpeakers = get()) }
}