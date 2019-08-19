package `in`.droidcon.info.team.di

import `in`.droidcon.info.team.domain.GetAllTeamMembers
import `in`.droidcon.info.team.domain.GetOneTeamMember
import `in`.droidcon.info.team.presentation.TeamDetailViewModel
import `in`.droidcon.info.team.repository.TeamRepository
import `in`.droidcon.info.team.repository.TeamRepositoryImpl
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val teamModule = module {

    factory { TeamRepositoryImpl() }
    factory<TeamRepository> { TeamRepositoryImpl() }
    factory { GetAllTeamMembers(executionThread = get(), teamRepository = get()) }
    factory { GetOneTeamMember(executionThread = get(), teamRepository = get()) }
    viewModel { TeamDetailViewModel(getOneTeamMember = get()) }
}