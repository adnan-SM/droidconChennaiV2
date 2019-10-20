package `in`.droidcon.base.model

/**
 * Only with the help of default values, we can directly map to objects from
 * firebase firestore
 * https://stackoverflow.com/questions/46633412/firebase-firestore-toobject-with-kotlin?rq=1
 */
data class SpeakerEntity(

    val speakerId: String = "",

    val speakerName: String = "",

    val speakerOrg: String = "",

    val speakerLocation: String = "",

    val speakerOneLiner: String = "",

    val speakerHandle: String = "",

    val speakerBlurbs: String = "",

    val speakerImg: String = "",

    val talkId: String = ""
)