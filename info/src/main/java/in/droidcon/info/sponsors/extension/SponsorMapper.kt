package `in`.droidcon.info.sponsors.extension

import `in`.droidcon.base.model.GridItem
import `in`.droidcon.info.sponsors.model.SponsorsEntity

/**
 * Created by Hari on 2019-08-22.
 * Mapper for sponsors
 */
fun SponsorsEntity.mapToDomain(): GridItem {
    return GridItem(
        this.id,
        this.name,
        this.role,
        this.country,
        null,
        null,
        this.website,
        this.blob,
        this.avatar,
        null
    )
}