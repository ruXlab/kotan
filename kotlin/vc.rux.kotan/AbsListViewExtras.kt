package vc.rux.kotan

import android.view.View
import android.widget.AbsListView
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener


/**
 * Add item click listener which accepts params: entity and position
 */
public inline fun AbsListView.onItemClick<reified T>(@inlineOptions(InlineOption.ONLY_LOCAL_RETURN) action: (T, Int) -> Unit): AbsListView {
    setOnItemClickListener(object: OnItemClickListener {
        override fun onItemClick(parent: AdapterView<out Adapter?>, view: View?, position: Int, id: Long) {
            action(parent?.getAdapter()?.getItem(position) as T, position)
        }
    })
    return this
}


/**
 * Add item click listener which accepts entity as generic
 */
public inline fun AbsListView.onItemClick<reified T>(@inlineOptions(InlineOption.ONLY_LOCAL_RETURN) action: (T) -> Unit): AbsListView {
    setOnItemClickListener(object: OnItemClickListener {
        override fun onItemClick(parent: AdapterView<out Adapter?>, view: View?, position: Int, id: Long) {
            action.invoke(parent.getAdapter()?.getItem(position) as T)
        }
    })
    return this
}


/**
 * Add item click listener which accepts entity as generic
 */
public inline fun AbsListView.onLongItemClick<reified T>(@inlineOptions(InlineOption.ONLY_LOCAL_RETURN) action: (T) -> Unit): AbsListView {
    setOnItemLongClickListener(object: AdapterView.OnItemLongClickListener {
        override fun onItemLongClick(parent: AdapterView<*>, view: View?, position: Int, id: Long): Boolean {
            action.invoke(parent.getAdapter()?.getItem(position) as T)
            return true
        }
    })
    return this
}

// not working yet
//public inline fun ListView.onItemClick<reified T>([inlineOptions(InlineOption.ONLY_LOCAL_RETURN)] action: (T) -> Unit): ListView {
//    return onItemClick<T> { (item: T, position: Int) -> action(item) }
//}

