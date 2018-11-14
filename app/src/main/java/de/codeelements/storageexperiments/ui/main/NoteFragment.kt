package de.codeelements.storageexperiments.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.codeelements.storageexperiments.R

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
class NoteFragment() : Fragment() {

    companion object {
        fun newInstance() = NoteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }
}

