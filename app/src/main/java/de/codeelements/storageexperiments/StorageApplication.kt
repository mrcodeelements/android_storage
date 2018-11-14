package de.codeelements.storageexperiments

import android.app.Application
import de.codeelements.storageexperiments.storage.StorageProvider

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
class StorageApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        StorageProvider.init(this)
    }
}