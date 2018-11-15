package de.codeelements.storageexperiments.ui.notelist

import android.view.LayoutInflater
import android.view.ViewGroup
import de.codeelements.storageexperiments.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * Created by MaikRiechel (Codeelements) on 15.11.2018.
 */
class NoteListViewHolder(
    inflater: LayoutInflater,
    parent: ViewGroup?,
    val notesListViewModel: NotesListViewModel
) {
    val view = inflater.inflate(R.layout.fragment_list, parent, false)
    val noteAdapter = NoteAdapter().also {
        view.recycler.adapter = it
    }

    private val compositeDisposable = CompositeDisposable(
        notesListViewModel.notesObservable.subscribe {
            noteAdapter.items = it
        }
    )

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}