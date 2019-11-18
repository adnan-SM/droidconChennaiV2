package `in`.droidcon.info.team.di

import `in`.droidcon.info.team.domain.GetAllTeamMembers
import `in`.droidcon.info.team.repository.TeamRepository
import `in`.droidcon.info.team.repository.TeamRepositoryImpl
import org.koin.dsl.module.module

val teamModule = module {
    factory<TeamRepository> { TeamRepositoryImpl() }
    factory { GetAllTeamMembers(executionThread = get(), teamRepository = get()) }
}