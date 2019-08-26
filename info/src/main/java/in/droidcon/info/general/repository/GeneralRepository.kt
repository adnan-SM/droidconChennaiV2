package `in`.droidcon.info.general.repository

import `in`.droidcon.info.common.model.InfoEntity
import io.reactivex.Single

/**
 * Created by Hari on 2019-08-24.
 * General category repository
 */
interface GeneralRepository {

    fun getGeneralDetails(): Single<List<InfoEntity>>
}