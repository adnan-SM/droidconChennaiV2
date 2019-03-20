package `in`.droidcon.base.core

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