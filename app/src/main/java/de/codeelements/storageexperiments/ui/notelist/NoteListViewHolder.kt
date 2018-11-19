package de.codeelements.storageexperiments.ui.notelist

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
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
    val noteAdapter = NoteAdapter().also { noteAdapter ->
        val recycler = view.recycler
        recycler.adapter = noteAdapter
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                log("Move")
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
                noteAdapter.notifyItemRemoved(position)
                (viewHolder as NoteViewHolder).item?.also {
                    notesListViewModel.remove(it)
                }
            }

        }).attachToRecyclerView(recycler)

    }

    private fun log(msg: String) {
        Log.d(NoteListViewHolder::class.java.simpleName, msg)
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