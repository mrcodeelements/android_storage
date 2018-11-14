package de.codeelements.storageexperiments.storage.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
@Entity
data class ObjectboxNote(
    @Id var id: Long,
    var title: String,
    var text: String
)
