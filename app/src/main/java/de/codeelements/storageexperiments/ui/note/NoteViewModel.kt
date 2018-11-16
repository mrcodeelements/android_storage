package de.codeelements.storageexperiments.ui.note

import android.arch.lifecycle.ViewModel
import de.codeelements.storageexperiments.model.Note
import de.codeelements.storageexperiments.storage.StorageProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class NoteViewModel : ViewModel() {

    private val storage
        get() = StorageProvider.storage

    private val noteSubject = BehaviorSubject.createDefault<Note>(Note())

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    fun setCurrentNoteId(noteId: Long) {
        noteId.takeIf { it >= 0 }?.also {
            compositeDisposable.add(
                storage.observeNote(it).take(1)
                    .subscribe { note ->
                        noteSubject.onNext(note)
                    }
            )
        }
    }

    fun storeNote(title: String, text: String) {
        noteSubject.value?.also {
            storage.store(it.copy(title = title, text = text))
        }
    }

    val noteObservable get() = noteSubject as Observable<Note>
}
