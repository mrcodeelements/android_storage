package de.codeelements.storageexperiments.storage.objectbox

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
@Entity
data class ObjectBoxNote(
    @Id var id: Long? = null,
    var title: String? = null,
    var text: String? = null
)
