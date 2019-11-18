package `in`.droidcon.info.general.di

import `in`.droidcon.info.general.domain.GetAllGeneralDetails
import `in`.droidcon.info.general.repository.GeneralRepository
import `in`.droidcon.info.general.repository.GeneralRepositoryImpl
import org.koin.dsl.module.module

/**
 * Created by Hari on 2019-08-24.
 * General koin module
 */
val generalModule = module {

    factory<GeneralRepository> { GeneralRepositoryImpl() }
    factory { GetAllGeneralDetails(executionThread = get(), generalRepository = get()) }
}