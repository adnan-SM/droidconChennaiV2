package `in`.droidcon.info.event.di

import `in`.droidcon.info.common.epoxy.controller.InfoController
import `in`.droidcon.info.event.domain.GetAllEventDetails
import `in`.droidcon.info.event.repository.EventRepository
import `in`.droidcon.info.event.repository.EventRepositoryImpl
import org.koin.dsl.module.module

/**
 * Created by Backbase R&D B.V on 2019-08-14.
 * event module for Koin
 */
val eventModule = module {

    factory<EventRepository> { EventRepositoryImpl() }
    factory { GetAllEventDetails(executionThread = get(), eventRepository = get()) }

    factory { (context: InfoController.InfoCallbacks) -> InfoController(context) }
}