package de.codeelements.storageexperiments.storage.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import de.codeelements.storageexperiments.model.Note

@Entity
data class RoomNote(
    @PrimaryKey var id: Long?,
    var title: String?,
    var text: String?
) {
    constructor(note: Note) : this(note.id, note.title, note.text)
}
