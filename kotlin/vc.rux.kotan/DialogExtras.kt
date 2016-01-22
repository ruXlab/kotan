package vc.rux.kotan

import android.content.DialogInterface

public fun onDialogClickListener(action: () -> Unit): DialogInterface.OnClickListener {
    return DialogInterface.OnClickListener { p0, button -> action() }
}




