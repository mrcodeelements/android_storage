package de.codeelements.storageexperiments.storage.room

import android.arch.persistence.room.*
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

    @Delete
    fun delete(vararg notes: RoomNote)
}
