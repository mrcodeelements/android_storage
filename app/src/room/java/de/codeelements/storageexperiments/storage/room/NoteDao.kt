package de.codeelements.storageexperiments.storage.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@Dao
interface NoteDao {
    @Query("SELECT * FROM roomNote")
    fun getAll(): Flowable<List<RoomNote>>

    @Query("SELECT * FROM roomnote WHERE id = :id")
    fun get(id: Long): Flowable<RoomNote>

    @Insert
    fun insert(vararg notes: RoomNote)

    @Update
    fun update(vararg notes: RoomNote)
}
