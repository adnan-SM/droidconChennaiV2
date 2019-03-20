package `in`.droidcon.speakers.model

data class SpeakerItem(

    val speakerId: String,

    val speakerName: String,

    val speakerOrg: String,

    val speakerLocation: String,

    val speakerOneLiner: String,

    val speakerHandle: String,

    val speakerBlurbs: String,

    val speakerImg: String,

    val talkId: List<String>
)