package de.codeelements.storageexperiments.storage

import android.app.Application
import io.realm.Realm

class RealmStorage(application: Application) : Storage {
    init {
        Realm.init(application)
    }
}
