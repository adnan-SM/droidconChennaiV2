package `in`.droidcon.info.mock

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.info.common.model.InfoEntity
import `in`.droidcon.info.sponsors.model.SponsorsEntity
import `in`.droidcon.info.team.model.TeamEntity
import `in`.droidcon.speakers.mock.MockData

/**
 * Created by Hari on 2019-07-24.
 * Factory to create fake data
 */
object FactoryOutlet {

    fun makeSpeakerEntity(): TeamEntity {
        return TeamEntity(
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString()
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

    fun makeInfoEntity(): InfoEntity {
        return InfoEntity(
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString()
        )
    }

    fun makeSponsorsEntity(): SponsorsEntity {
        return SponsorsEntity(
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString(),
            MockData.randomString()
        )
    }
}