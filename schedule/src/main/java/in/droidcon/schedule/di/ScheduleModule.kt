package `in`.droidcon.schedule.di

import `in`.droidcon.schedule.domain.GetDayOneSchedule
import `in`.droidcon.schedule.domain.GetDayTwoSchedule
import `in`.droidcon.schedule.domain.QuerySpeakers
import `in`.droidcon.schedule.epoxy.ScheduleController
import `in`.droidcon.schedule.epoxy.ScheduleDetailController
import `in`.droidcon.schedule.presentation.ScheduleViewModel
import `in`.droidcon.schedule.repository.ScheduleRepository
import `in`.droidcon.schedule.repository.ScheduleRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by Hari on 2019-10-12.
 * Module for schedule
 */
val scheduleModule = module {
    single { FirebaseFirestore.getInstance() }
    factory<ScheduleRepository> { ScheduleRepositoryImpl(get()) }
    factory { GetDayOneSchedule(executionThread = get(), scheduleRepository = get()) }
    factory { GetDayTwoSchedule(executionThread = get(), scheduleRepository = get()) }
    factory { QuerySpeakers(executionThread = get(), scheduleRepository = get()) }
    factory { (context: ScheduleController.ScheduleCallbacks) ->  ScheduleController(context) }
    viewModel { ScheduleViewModel(get(), get(), get()) }
    factory { (context: ScheduleDetailController.ScheduleCallbacks) -> ScheduleDetailController(context) }
}