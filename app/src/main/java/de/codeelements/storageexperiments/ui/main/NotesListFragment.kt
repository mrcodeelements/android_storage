package de.codeelements.storageexperiments.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.codeelements.storageexperiments.R
import de.codeelements.storageexperiments.createNoteActivityIntent
import kotlinx.android.synthetic.main.fragment_list.*

class NotesListFragment : Fragment() {

    companion object {
        fun newInstance() = NotesListFragment()
    }

    private lateinit var viewModel: NotesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collapsingToolbarLayout.title = getText(R.string.app_name)

        actionAdd.setOnClickListener {
            context?.run {
                startActivity(
                    createNoteActivityIntent()
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(NotesListViewModel::class.java)
    }

}
