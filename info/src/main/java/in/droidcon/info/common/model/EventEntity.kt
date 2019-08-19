package `in`.droidcon.info.common.model

/**
 * Created by Hari on 2019-08-14.
 * Only with the help of default values, we can directly map to objects from
 * firebase firestore
 * https://stackoverflow.com/questions/46633412/firebase-firestore-toobject-with-kotlin?rq=1
 */
data class EventEntity(

    val type: String = "",

    val title: String = "",

    val desc: String? = null,

    val desc2: String? = null,

    val redirectText: String? = null,

    val redirectUrl: String? = null,

    val destination: String? = null,

    val copy: String? = null,

    val buttonType: String? = null,

    val buttonText: String? = null
)