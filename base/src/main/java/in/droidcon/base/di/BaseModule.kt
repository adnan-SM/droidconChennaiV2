package `in`.droidcon.base.di

import `in`.droidcon.base.adapter.GridListAdapter
import `in`.droidcon.base.epoxy.controller.GridDetailController
import `in`.droidcon.base.thread.ExecutionThread
import `in`.droidcon.base.thread.RxThread
import org.koin.dsl.module.module

/**
 * @author Adnan A M
 * @since  26/03/19
 */
val baseModule = module {

    single<ExecutionThread> { RxThread() }

    factory { (context: GridDetailController.GridDetailCallbacks) -> GridDetailController(context) }

    factory { (context: GridListAdapter.ListItemClickListener) -> GridListAdapter(context) }
}