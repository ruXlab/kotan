package vc.rux.kotan

import android.app.Activity
import android.view.View
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


@Deprecated("User Android-supplied typesafe findViewById", replaceWith = ReplaceWith("findViewById"))
inline fun <reified T : View> Activity.findView(id: Int): T {
    return findViewById(id)
}


class LazyViewOptional<out T: View>(private val viewId: Int) : ReadOnlyProperty<Activity, T?> {
    private var value: T? = null

    override fun getValue(thisRef: Activity, desc: KProperty<*>): T? {
        if (value == null)
            value = thisRef.findViewById<T>(viewId)
        return value
    }
}
