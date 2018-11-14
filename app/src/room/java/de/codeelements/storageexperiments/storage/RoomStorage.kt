package de.codeelements.storageexperiments.storage

import android.app.Application
import android.arch.persistence.room.Room
import de.codeelements.storageexperiments.storage.room.AppDatabase

class RoomStorage(application: Application) : Storage {

    val database = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "room.db"
    ).build()

}
