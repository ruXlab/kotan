package vc.rux.kotan

import android.view.View
import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import android.app.Fragment


public inline fun Activity.findView<reified T : View?>(id: Int): T {
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


class LazyViewOptional<T>(private val viewId: Int) : ReadOnlyProperty<Activity, T?> {
    private var value: T? = null

    override fun get(thisRef: Activity, desc: PropertyMetadata): T? {
        if (value == null)
            value = thisRef.findViewById(viewId) as T?
        return value
    }
}