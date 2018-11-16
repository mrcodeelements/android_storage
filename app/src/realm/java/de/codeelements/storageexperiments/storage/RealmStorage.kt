package de.codeelements.storageexperiments.storage

import android.app.Application
import de.codeelements.storageexperiments.model.Note
import de.codeelements.storageexperiments.storage.realm.RealmNote
import io.reactivex.Observable
import io.realm.Realm

class RealmStorage(application: Application) : Storage {

    init {
        Realm.init(application)
    }

    private val realm: Realm
        get() = Realm.getDefaultInstance()

    override val notesObservable: Observable<List<Note>>
        get() = realm.where(RealmNote::class.java).findAllAsync().asFlowable()
            .filter {
                it.isLoaded
            }.flatMap {
                Observable.fromIterable(it)
                    .map { realmNote ->
                        realmNote.toNote()
                    }.collectInto(mutableListOf()) { list: MutableList<Note>, element ->
                        list.add(element)
                    }.map { mutableList ->
                        mutableList.toList()
                    }.toFlowable()
            }.toObservable()

    override fun observeNote(id: Long): Observable<Note> = realm
        .where(RealmNote::class.java).equalTo("id", id).findFirstAsync()
        .asFlowable<RealmNote>()
        .filter {
            it.isLoaded
        }.map {
            it.toNote()
        }.toObservable()

    override fun store(note: Note) {
        realm.use { managedRealm ->
            managedRealm.executeTransactionAsync { asyncRealm ->
                asyncRealm.insertOrUpdate(RealmNote(note))
            }
        }
    }
}

fun RealmNote.toNote() = Note(id, title, text)