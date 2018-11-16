package de.codeelements.storageexperiments.storage.realm

import de.codeelements.storageexperiments.model.Note
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
open class RealmNote(
    @PrimaryKey var id: Long? = null,
    var title: String? = null,
    var text: String? = null
) : RealmObject() {
    constructor(note: Note) : this(note.id ?: UUID.randomUUID().leastSignificantBits, note.title, note.text)
}