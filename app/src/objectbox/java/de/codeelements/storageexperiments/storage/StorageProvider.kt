package de.codeelements.storageexperiments.storage

import android.app.Application

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
object StorageProvider {

    lateinit var storage: Storage
        private set

    fun init(application: Application) {

        storage = ObjectBoxStorage(application)
    }

}