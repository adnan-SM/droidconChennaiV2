package `in`.droidcon.base.extension

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

fun FirebaseFirestore.getSpeakerList(): Task<QuerySnapshot> {
    return this.collection("speakers").get()
}

fun FirebaseFirestore.getOneSpeaker(id: String): Task<DocumentSnapshot> {
    return this.collection("speakers")
        .document(id)
        .get()
}

fun FirebaseFirestore.getTeamList(): Task<QuerySnapshot> {
    return this.collection("team").get()
}

fun FirebaseFirestore.getOneTeamMember(id: String): Task<DocumentSnapshot> {
    return this.collection("team")
        .document(id)
        .get()
}

fun FirebaseFirestore.getEventDetails(): Task<QuerySnapshot> {
    return this.collection("event").get()
}

fun FirebaseFirestore.getGeneralDetails(): Task<QuerySnapshot> {
    return this.collection("general").get()
}

fun FirebaseFirestore.getsponsorList(): Task<QuerySnapshot> {
    return this.collection("sponsors").get()
}

fun FirebaseFirestore.getOneSponsor(id: String): Task<DocumentSnapshot> {
    return this.collection("sponsors")
        .document(id)
        .get()
}