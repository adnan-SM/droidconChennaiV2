package `in`.droidcon.speakers.mock

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.data.speakers.model.SpeakerEntity

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

    fun makeGridItem(): GridItem {
        return GridItem(
            MockData.randomString(),
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