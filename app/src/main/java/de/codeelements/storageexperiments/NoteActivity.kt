package de.codeelements.storageexperiments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.codeelements.storageexperiments.ui.main.NoteViewModel

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
class NoteActivity : AppCompatActivity() {

    companion object {
        const val ARG_NOTE_ID = "noteId"
    }

    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteViewModel = ViewModelProviders.of(this)[NoteViewModel::class.java]
        if (intent.hasExtra(ARG_NOTE_ID)) {
            noteViewModel.setCurrentNoteId(intent.getLongExtra(ARG_NOTE_ID, -1))
        }
        setContentView(R.layout.activity_note)
    }
}

fun Context.createNoteActivityIntent(noteId: Long? = null) = Intent(this, NoteActivity::class.java).apply {
    noteId?.also {
        putExtra(NoteActivity.ARG_NOTE_ID, it)
    }
}