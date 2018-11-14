package de.codeelements.storageexperiments.storage

import android.app.Application
import de.codeelements.storageexperiments.storage.objectbox.MyObjectBox

class ObjectBoxStorage(application: Application) : Storage {
    val objectBox = MyObjectBox.builder().androidContext(application).build()
}