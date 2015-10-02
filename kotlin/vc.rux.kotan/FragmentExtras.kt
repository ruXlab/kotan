package vc.rux.kotan

import android.app.Fragment
import android.view.View
import kotlin.properties.ReadOnlyProperty
import android.app.DialogFragment
import kotlin.properties.Delegates

public inline fun Fragment.findView<reified T : View?>(id: Int): T {
    return getView().findView<T>(id)
}

class FragmentLazyView<T : View>(private val viewId: Int) : ReadOnlyProperty<Fragment, T> {
    private var value by Delegates.notNull<T>()

    override fun get(thisRef: Fragment, desc: PropertyMetadata): T {
        if (value == null)
            value = thisRef.getView().findViewById(viewId) as T
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