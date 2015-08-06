package vc.rux.kotan

import android.app.Activity
import android.content.Context
import android.widget.Toast

inline fun toast(context: Context, text: CharSequence, length: Int) {
    Toast.makeText(context, text, length).show()
}

fun toastLong(context: Context, text: CharSequence) =
    toast(context, text, Toast.LENGTH_LONG)

fun toastShort(context: Context, text: CharSequence) =
    toast(context, text, Toast.LENGTH_SHORT)

fun toastLong(context: Context, textResourceId: Int) =
    toast(context, context.getString(textResourceId), Toast.LENGTH_LONG)

fun toastShort(context: Context, textResourceId: Int) =
    toast(context, context.getString(textResourceId), Toast.LENGTH_SHORT)

fun Activity.toastLong(text: CharSequence) =
    toastLong(this, text)

fun Activity.toastShort(text: CharSequence) =
    toastShort(this, text)

fun Activity.toastLong(textResourceId: Int) =
    toastLong(this, textResourceId)

fun Activity.toastShort(textResourceId: Int) =
    toastShort(this, textResourceId)
