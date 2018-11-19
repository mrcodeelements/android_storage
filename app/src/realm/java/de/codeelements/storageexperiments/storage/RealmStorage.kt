package de.codeelements.storageexperiments.storage

import android.app.Application
import de.codeelements.storageexperiments.model.Note
import de.codeelements.storageexperiments.storage.realm.RealmNote
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
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

    override fun observeNote(id: Long): Observable<Note> = realm.queryNote(id).findFirstAsync()
        .asFlowable<RealmNote>()
        .filter {
            it.isLoaded
        }.map {
            it.toNote()
        }.toObservable()

    private fun Realm.queryNote(id: Long) = where(RealmNote::class.java).equalTo("id", id)

    override fun store(note: Note): Completable =
        Completable.fromAction {
            realm.use { managedRealm ->
                managedRealm.executeTransaction { transactionRealm ->
                    transactionRealm.insertOrUpdate(RealmNote(note))
                }
            }
        }.subscribeOn(Schedulers.io())

    override fun remove(note: Note): Completable =
        Completable.fromAction {
            realm.use {
                it.executeTransaction {
                    it.queryNote(note.id!!).findAll().deleteAllFromRealm()
                }
            }
        }.subscribeOn(Schedulers.io())
}

fun RealmNote.toNote() = Note(id, title, text)
