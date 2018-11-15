package de.codeelements.storageexperiments.storage

import de.codeelements.storageexperiments.model.Note
import io.reactivex.Observable

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
interface Storage {
    val notesObservable: Observable<List<Note>>

    fun observeNote(id: Long): Observable<Note>
    fun store(note: Note)
}