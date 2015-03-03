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
import android.content.DialogInterface

public fun onDialogClickListener(action: () -> Unit): DialogInterface.OnClickListener {
    return object : DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface, button: Int) {
            action()
        }
    }
}




