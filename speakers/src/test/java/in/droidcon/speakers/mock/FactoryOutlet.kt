package `in`.droidcon.speakers.mock

import `in`.droidcon.data.speakers.model.SpeakerEntity
import `in`.droidcon.speakers.model.SpeakerItem

/**
 * Created by Hari on 2019-07-24.
 * Factory to create fake data
 */
object FactoryOutlet {

    fun makeSpeakerEntity(): SpeakerEntity {
        return SpeakerEntity(
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            listOf(MockData.randomString())
        )
    }

    fun makeSpeakerItem(): SpeakerItem {
        return SpeakerItem(
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            listOf(MockData.randomString())
        )
    }
}