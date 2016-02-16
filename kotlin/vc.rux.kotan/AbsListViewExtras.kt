package vc.rux.kotan

import android.view.View
import android.widget.AbsListView
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener


/**
 * Add item click listener which accepts params: entity and position
 */
public inline fun <reified T> AbsListView.onItemClick(crossinline action: (T, Int) -> Unit): AbsListView {
    onItemClickListener = OnItemClickListener { parent, view, position, id ->
        action(parent.adapter.getItem(position) as T, position)
    }
    return this
}


/**
 * Add item click listener which accepts entity as generic
 */
public inline fun <reified T> AbsListView.onItemClick(crossinline action: (T) -> Unit): AbsListView {
    onItemClickListener = OnItemClickListener { parent, view, position, id ->
        action.invoke(parent.adapter.getItem(position) as T)
    }
    return this
}


/**
 * Add item click listener which accepts entity as generic
 */
public inline fun <reified T> AbsListView.onLongItemClick(crossinline action: (T) -> Unit): AbsListView {
    setOnItemLongClickListener({ parent, view, position, id ->
        action.invoke(parent.adapter.getItem(position) as T)
        true
    })
    return this
}

// not working yet
//public inline fun ListView.onItemClick<reified T>([inlineOptions(InlineOption.ONLY_LOCAL_RETURN)] action: (T) -> Unit): ListView {
//    return onItemClick<T> { (item: T, position: Int) -> action(item) }
//}

