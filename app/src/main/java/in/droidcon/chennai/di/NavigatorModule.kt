package `in`.droidcon.chennai.di

import `in`.droidcon.chennai.Navigator
import org.koin.dsl.module.module

/**
 * @author Adnan A M
 * @since  26/03/19
 */
val navModule = module {

    factory { Navigator() }
}