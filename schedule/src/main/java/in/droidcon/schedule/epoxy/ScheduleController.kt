package `in`.droidcon.schedule.epoxy

import `in`.droidcon.base.epoxy.model.schedule
import `in`.droidcon.base.epoxy.model.time
import `in`.droidcon.base.util.CategoryUtil
import `in`.droidcon.schedule.model.ScheduleEntity
import `in`.droidcon.schedule.model.TalkEntity
import com.airbnb.epoxy.TypedEpoxyController

/**
 * Created by Hari on 2019-10-13.
 * Epoxy schedule controller
 */
class ScheduleController(private val callbacks: ScheduleCallbacks): TypedEpoxyController<List<ScheduleEntity>>() {

    override fun buildModels(data: List<ScheduleEntity>) {
        data.forEach {
            time {
                id(it.hashCode())
                time(it.time)
            }
            it.talks.forEach {
                it?.let { item ->
                    schedule {
                        val talk = "${item.duration} | ${item.track}"
                        val categoryColors = CategoryUtil.categoryMap[item.category]
                        val speakerNames = item.speakerNames.toString()
                            .removeSurrounding("[", "]")
                        id(item.hashCode())
                        talkTitle(item.title)
                        speaker(speakerNames)
                        talkDetails(talk)
                        category(item.category)
                        clickListener { _ -> callbacks.onScheduleClicked(item)}
                        if (categoryColors != null) {
                            categoryBgColor(categoryColors.bgColor)
                            categoryTextColor(categoryColors.txtColor)
                        }
                    }
                }
            }
        }
    }

    interface ScheduleCallbacks {
        fun onScheduleClicked(entity: TalkEntity)
    }
}