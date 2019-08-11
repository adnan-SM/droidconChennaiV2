package `in`.droidcon.info.team.model

/**
 * Only with the help of default values, we can directly map to objects from
 * firebase firestore
 * https://stackoverflow.com/questions/46633412/firebase-firestore-toobject-with-kotlin?rq=1
 */
data class TeamEntity(

    val id: String = "",

    val name: String = "",

    val company: String = "",

    val city: String = "",

    val handle: String = "",

    val blob: String = "",

    val avatar: String = ""
)