package `in`.droidcon.info.common.di

import `in`.droidcon.info.common.presentation.InfoViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Hari on 2019-08-16.
 * Info module dependencies
 */

val infoModule = module {

    viewModel {
        InfoViewModel(
            getEventDetails = get(),
            getTeam = get(),
            getAllSponsors = get(),
            getAllGeneralDetails = get()
        )
    }
}