package de.codeelements.storageexperiments.storage.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
@Database(entities = [RoomNote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}