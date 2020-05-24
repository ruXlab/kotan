package vc.rux.kotan

import android.widget.AbsListView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView


/**
 * Add item click listener which accepts params: entity and position
 */
inline fun <reified T> AbsListView.onItemClick(crossinline action: (T, Int) -> Unit): AbsListView {
    onItemClickListener = OnItemClickListener { parent, _, position, _ ->
        action(parent.adapter.getItem(position) as T, position)
    }
    return this
}


/**
 * Add item click listener which accepts entity as generic
 */
inline fun <reified T> AbsListView.onItemClick(crossinline action: (T) -> Unit): AbsListView {
    onItemClickListener = OnItemClickListener { parent, _, position, _ ->
        action.invoke(parent.adapter.getItem(position) as T)
    }
    return this
}


/**
 * Add item click listener which accepts entity as generic
 */
inline fun <reified T> AbsListView.onLongItemClick(crossinline action: (T) -> Unit): AbsListView {
    setOnItemLongClickListener { parent, _, position, _ ->
        action.invoke(parent.adapter.getItem(position) as T)
        true
    }
    return this
}
