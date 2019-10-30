package `in`.droidcon.base.util

import `in`.droidcon.base.R

/**
 * Created by Hari on 2019-10-13.
 * category utils
 */
object CategoryUtil {

    val categoryMap = mapOf<String, CategoryColors>(
        "Android" to CategoryColors(R.color.android, R.color.android_text, R.drawable.ic_droid),
        "Firebase" to CategoryColors(R.color.firebase, R.color.firebase_text, R.drawable.ic_firebase),
        "Flutter" to CategoryColors(R.color.flutter, R.color.flutter_text, R.drawable.flutter),
        "ARCore" to CategoryColors(R.color.arcore, R.color.arcore_text, R.drawable.ic_arcore),
        "Tensorflow" to CategoryColors(R.color.tensorflow, R.color.tensorflow_text, R.drawable.tensorflow),
        "Workshop" to CategoryColors(R.color.tensorflow, R.color.tensorflow_text, R.drawable.ic_workshop),
        "Keynote" to CategoryColors(R.color.keynote, R.color.keynote_text, R.drawable.ic_keynote),
        "Open Mic" to CategoryColors(R.color.colorTwitter, R.color.flutter_text, R.drawable.ic_keynote),
        "Design" to CategoryColors(R.color.design, R.color.design_text, R.drawable.ic_material_logo)
    )

    data class CategoryColors(val bgColor: Int, val txtColor: Int, val drawable: Int)
}