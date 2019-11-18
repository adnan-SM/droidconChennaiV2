package `in`.droidcon.chennai.di

import `in`.droidcon.base.navigation.MainNavigation
import `in`.droidcon.chennai.navigation.Navigator
import `in`.droidcon.chennai.navigation.ScheduleNavigator
import `in`.droidcon.schedule.navigation.ScheduleNavigation
import org.koin.dsl.definition.Kind
import org.koin.dsl.module.module

/**
 * @author Adnan A M
 * @since  26/03/19
 */
val navModule = module {

    single<MainNavigation> { Navigator() }

    factory<ScheduleNavigation> { ScheduleNavigator(get()) }
}