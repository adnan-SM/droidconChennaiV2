package `in`.droidcon.schedule.repository

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.schedule.mock.FactoryOutlet
import `in`.droidcon.schedule.model.ScheduleEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Hari on 2019-10-12.
 * Schedule repository impl test
 */
@RunWith(JUnit4::class)
class ScheduleRepositoryImplTest {

    private val firestore: FirebaseFirestore = mock()
    private val scheduleDataRepository = ScheduleRepositoryImpl(firestore)
    private val repository: ScheduleRepository = mock()

    @Test
    fun `get schedule completes`() {
        stubScheduleResponse(Single.just(listOf(FactoryOutlet.makeScheduleEntity())))
        scheduleDataRepository.getDayOneSchedule()
        repository.getDayOneSchedule().test().assertComplete()
    }

    @Test
    fun `get schedule returns data`() {
        val response = listOf(FactoryOutlet.makeScheduleEntity())
        stubScheduleResponse(Single.just(response))
        scheduleDataRepository.getDayOneSchedule()
        repository.getDayOneSchedule().test().assertValue(response)
    }

    private fun stubScheduleResponse(thisThing: Single<List<ScheduleEntity>>) {
        whenever(repository.getDayOneSchedule()).thenReturn(thisThing)
    }

}