package vc.rux.kotan

import kotlin.properties.ReadOnlyProperty
import android.app.Activity
import android.app.Fragment
import android.view.View

/**
 * Helper class, implements findView for different types of UI object
 */
private abstract class FindViewBase<T>(private val viewId: Int) : ReadOnlyProperty<Any, T> {
    protected fun findById(thisRef: Any): T =
        if (thisRef is Activity)
            thisRef.findViewById(viewId) as T
        else if (thisRef is Fragment)
            thisRef.getView().findViewById(viewId) as T
        else if (thisRef is View)
            thisRef.findViewById(viewId) as T
        else throw IllegalArgumentException("Delegate can't be attached to this object")

}

/**
 * Lazy implementation of findView. Will be evaluated on first access
 */
class LazyView<T>(private val viewId: Int) : FindViewBase<T>(viewId) {
    private var value: T = null

    override fun get(thisRef: Any, desc: PropertyMetadata): T {
        if (value == null) value = findById(viewId)
        return value
    }
}

/**
 * Find UI component
 */
class FindView<T>(private val viewId: Int) : FindViewBase<T>(viewId) {
    private val value: T = findById(viewId)

    override fun get(thisRef: Any, desc: PropertyMetadata): T {
        return value
    }
}