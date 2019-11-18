package `in`.droidcon.schedule.mock

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.schedule.mock.MockData
import `in`.droidcon.schedule.model.ScheduleEntity
import `in`.droidcon.schedule.model.TalkEntity

/**
 * Created by Hari on 2019-07-24.
 * Factory to create fake data
 */
object FactoryOutlet {

    fun makeTalkEntity(): TalkEntity {
        return TalkEntity(
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            listOf(MockData.randomString()),
            listOf(MockData.randomString())
        )
    }

    fun makeScheduleEntity(): ScheduleEntity {
        return ScheduleEntity(
            MockData.randomString(),
            listOf(makeTalkEntity())
        )
    }
}