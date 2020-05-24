package vc.rux.kotan

import android.app.Fragment
import android.view.View
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : View?> Fragment.findView(id: Int): T {
    return view.findView<T>(id)
}

class FragmentLazyView<T : View>(private val viewId: Int) : ReadOnlyProperty<Fragment, T> {
    private var value by Delegates.notNull<T>()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (value == null)
            value = thisRef.view.findViewById(viewId) as T
        return value
    }
}


//
//class LazyView<T>(private val viewId: Int) : ReadOnlyProperty<Fragment, T> {
//    private var value: T = null
//
//    override fun get(thisRef: Fragment, desc: PropertyMetadata): T {
//        if (value == null)
//            value = thisRef.findView<T.javaClass>(viewId)
//        return value
//    }
//}