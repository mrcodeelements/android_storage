package de.codeelements.storageexperiments.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by MaikRiechel (Codeelements) on 15.11.2018.
 */
fun ViewGroup.inflate(@LayoutRes layout: Int) =
    LayoutInflater.from(context)
        .inflate(layout, this, false)