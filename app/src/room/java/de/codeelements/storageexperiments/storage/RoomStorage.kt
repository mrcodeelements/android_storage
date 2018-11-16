package de.codeelements.storageexperiments.storage

import android.app.Application
import android.arch.persistence.room.Room
import de.codeelements.storageexperiments.model.Note
import de.codeelements.storageexperiments.storage.room.AppDatabase
import de.codeelements.storageexperiments.storage.room.RoomNote
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RoomStorage(application: Application) : Storage {
    val database = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "room.db"
    ).build()

    private val noteDao
        get() = database.noteDao()

    override val notesObservable: Observable<List<Note>>
        get() = noteDao.getAll().flatMap {
            Observable.fromIterable(it).map {
                it.toNote()
            }.collectInto(mutableListOf<Note>()) { list, note ->
                list += note
            }.map {
                it.toList()
            }.toFlowable()
        }.toObservable()

    override fun observeNote(id: Long): Observable<Note> = noteDao.get(id).map {
        it.toNote()
    }.toObservable()

    override fun store(note: Note) =
        Completable.fromAction {
            RoomNote(note).also {
                when (it.id) {
                    null -> noteDao.insert(it)
                    else -> noteDao.update(it)
                }
            }
        }.subscribeOn(Schedulers.io())
}

fun RoomNote.toNote() = Note(id, title, text)