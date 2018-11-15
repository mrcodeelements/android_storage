package de.codeelements.storageexperiments.ui.note

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import de.codeelements.storageexperiments.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_note.view.*

/**
 * Created by MaikRiechel (Codeelements) on 15.11.2018.
 */
class NoteViewHolder(
    inflater: LayoutInflater,
    container: ViewGroup?,
    val notesViewModel: NoteViewModel
) {
    val view = inflater.inflate(R.layout.fragment_note, container, false)

    private val compositeDisposable = CompositeDisposable(
        notesViewModel.noteObservable.observeOn(AndroidSchedulers.mainThread()).subscribe { note ->
            note.title?.also {
                view.inputTitle.text = SpannableStringBuilder.valueOf(it)
            }
            note.text?.also {
                view.inputText.text = SpannableStringBuilder.valueOf(it)
            }
        }
    )

    fun onDestroy() {
        compositeDisposable.dispose()

        val titleText = view.inputTitle.text
        val textText = view.inputText.text
        notesViewModel.storeNote(titleText.toString(), textText.toString())
    }


}