package `in`.droidcon.info.sponsors.di

import `in`.droidcon.info.sponsors.domain.GetAllSponsors
import `in`.droidcon.info.sponsors.repository.SponsorsRepository
import `in`.droidcon.info.sponsors.repository.SponsorsRepositoryImpl
import org.koin.dsl.module.module

val sponsorsModule = module {
    factory<SponsorsRepository> { SponsorsRepositoryImpl() }
    factory { GetAllSponsors(executionThread = get(), sponsorsRepository = get()) }
}