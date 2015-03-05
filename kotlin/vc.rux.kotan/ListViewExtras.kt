package vc.rux.kotan

import android.view.View
import android.app.Activity
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.AdapterView.OnItemClickListener
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ListView


/**
 * Add item click listener which accepts params: entity and position
 */
public inline fun ListView.onItemClick<reified T>([inlineOptions(InlineOption.ONLY_LOCAL_RETURN)]  action: (T, Int) -> Unit): ListView {
    setOnItemClickListener(object: OnItemClickListener {
        override fun onItemClick(parent: AdapterView<out Adapter?>, view: View?, position: Int, id: Long) {
            action(parent.getAdapter()?.getItem(position) as T, position)
        }
    })
    return this
}


/**
 * Add item click listener which accepts entity as param
 */
public inline fun ListView.onItemClick<reified T>([inlineOptions(InlineOption.ONLY_LOCAL_RETURN)] action: (T) -> Unit): ListView {
    setOnItemClickListener(object: OnItemClickListener {
        override fun onItemClick(parent: AdapterView<out Adapter?>, view: View?, position: Int, id: Long) {
            action.invoke(parent.getAdapter()?.getItem(position) as T)
        }
    })
    return this
}
// not working yet
//public inline fun ListView.onItemClick<reified T>([inlineOptions(InlineOption.ONLY_LOCAL_RETURN)] action: (T) -> Unit): ListView {
//    return onItemClick<T> { (item: T, position: Int) -> action(item) }
//}

