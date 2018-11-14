package de.codeelements.storageexperiments.storage

import android.app.Application

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
object StorageProvider {

    lateinit var storage: Storage

    public fun init(application: Application) {
        storage = RealmStorage(application)
    }
}