package de.codeelements.storageexperiments.ui.notelist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import de.codeelements.storageexperiments.R
import de.codeelements.storageexperiments.extension.inflate
import de.codeelements.storageexperiments.model.Note
import de.codeelements.storageexperiments.ui.ItemViewHolder
import de.codeelements.storageexperiments.ui.note.createNoteActivityIntent
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    var items: List<Note>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) =
        NoteViewHolder(parent)

    override fun getItemCount() = items?.size ?: 0

    override fun getItemId(position: Int): Long {
        return getItem(position)?.id ?: super.getItemId(position)
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        viewHolder.item = getItem(position)
    }

    private fun getItem(position: Int) = items?.takeIf { position < it.size }?.get(position)

}

class NoteViewHolder(parent: ViewGroup) : ItemViewHolder<Note>(parent.inflate(R.layout.item_note)) {

    private val titleView = itemView.title
    private val textView = itemView.text

    init {
        itemView.setOnClickListener { view ->
            item?.also { item ->
                view.context.apply {
                    startActivity(createNoteActivityIntent(item.id))
                }
            }
        }
    }

    override fun onBind(item: Note?) {
        item?.run {
            titleView.text = title
            textView.text = text
        }
    }
}
