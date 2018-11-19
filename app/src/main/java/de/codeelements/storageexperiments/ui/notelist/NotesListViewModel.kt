package de.codeelements.storageexperiments.ui.notelist

import android.arch.lifecycle.ViewModel
import de.codeelements.storageexperiments.model.Note
import de.codeelements.storageexperiments.storage.StorageProvider
import io.reactivex.android.schedulers.AndroidSchedulers

class NotesListViewModel : ViewModel() {
    private val storage = StorageProvider.storage

    val notesObservable = storage.notesObservable.observeOn(AndroidSchedulers.mainThread())

    fun remove(note: Note) {
        storage.remove(note).subscribe()
    }

}
