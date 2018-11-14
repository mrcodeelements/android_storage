package de.codeelements.storageexperiments.storage.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM roomNote")
    fun getAll(): List<RoomNote>

    @Insert
    fun insertAll(vararg notes: RoomNote)

}
