package `in`.droidcon.info.team.di

import org.koin.standalone.StandAloneContext.loadKoinModules

/**
 * Created by Hari on 2019-08-10.
 * team module for koin service locator
 */
object TeamKoin {

    fun init() = loadKoinModules(teamModule)
}