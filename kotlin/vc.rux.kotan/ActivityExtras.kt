package vc.rux.kotan

import android.view.View
import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import android.app.Fragment
import kotlin.reflect.KProperty


public inline fun <reified T : View> Activity.findView(id: Int): T {
    return findViewById(id) as T
}




/*
class LazyView<T>(private val viewId: Int) : ReadOnlyProperty<Activity, T> {
    private var value: T = null

    override fun get(thisRef: Activity, desc: PropertyMetadata): T {
        if (value == null)
            value = thisRef.findViewById(viewId) as T
        return value
    }
}

 */


class LazyViewOptional<out T: View>(private val viewId: Int) : ReadOnlyProperty<Activity, T?> {
    private var value: T? = null

    override fun getValue(thisRef: Activity, desc: KProperty<*>): T? {
        if (value == null)
            value = thisRef.findViewById<T>(viewId)
        return value
    }
}
