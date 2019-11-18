package `in`.droidcon.info.team.extension

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.info.team.model.TeamEntity

/**
 * Created by Hari on 2019-07-23.
 * Extensions for Team module
 */
fun TeamEntity.mapToDomain(): GridItem {
    return GridItem(
        this.id,
        this.name,
        this.company,
        this.city,
        null,
        this.handle,
        null,
        this.blob,
        this.avatar,
        null
    )
}