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


public fun onItemClickListener<T>(action: (T) -> Unit): OnItemClickListener {
    return object : OnItemClickListener {

        override fun onItemClick(parent: AdapterView<out Adapter?>, view: View?, position: Int, id: Long) {
            action(parent?.getAdapter()?.getItem(position) as T)
        }
    }
}


public fun ListView.setOnItemClickListener<T>(action: (T) -> Unit): Unit {
    setOnItemClickListener(onItemClickListener(action))
}



