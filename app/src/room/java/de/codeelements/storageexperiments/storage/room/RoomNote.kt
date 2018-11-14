package de.codeelements.storageexperiments.storage.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RoomNote(
    @PrimaryKey var id: Long,
    var title: String,
    var text: String
)
