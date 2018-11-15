package de.codeelements.storageexperiments.ui

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by MaikRiechel (Codeelements) on 15.11.2018.
 */
open class ItemViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var item: T? = null
        set(value) {
            field?.let {
                onUnbind(it)
            }
            field = value
            onBind(field)
        }

    protected open fun onUnbind(item: T) {
    }

    protected open fun onBind(item: T?) {
    }

}