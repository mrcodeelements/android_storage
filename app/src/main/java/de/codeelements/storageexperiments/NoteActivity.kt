package de.codeelements.storageexperiments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by MaikRiechel (Codeelements) on 14.11.2018.
 */
class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
    }
}

fun Context.createNoteActivityIntent() = Intent(this, NoteActivity::class.java)