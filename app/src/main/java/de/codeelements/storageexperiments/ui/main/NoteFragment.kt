package de.codeelements.storageexperiments.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
class NoteFragment : Fragment() {

    companion object {
        fun newInstance() = NoteFragment()
    }

    private lateinit var notesViewModel: NoteViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        notesViewModel = ViewModelProviders.of(activity!!)[NoteViewModel::class.java]
    }

    private var notesViewHolder: NotesViewHolder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        NotesViewHolder(inflater, container, notesViewModel).also { notesViewHolder = it }.view

    override fun onDestroyView() {
        super.onDestroyView()

        notesViewHolder = notesViewHolder?.let {
            it.onDestroy()
            null
        }
    }
}

