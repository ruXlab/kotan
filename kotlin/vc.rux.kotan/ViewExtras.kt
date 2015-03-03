package vc.rux.kotan

import android.view.View

/**
 * Set handler for click
 */
public fun View.onClick(inline clickHandler: () -> Unit): View {
    setOnClickListener(object: View.OnClickListener {
        override fun onClick(v: View?) {
            clickHandler()
        }
    })
    return this
}

/**
 * Set handler for long click
 */
public fun View.onLongClick(inline clickHandler: () -> Boolean): View {
    setOnLongClickListener(object: View.OnLongClickListener {
        override fun onLongClick(v: View?): Boolean {
            return clickHandler()
        }
    })
    return this
}

/**
 * Set view visibility state: visible or gone
 */
public fun View.setVisibility(isVisible: Boolean): View {
    setVisibility(if(isVisible) View.VISIBLE else View.GONE)
    return this
}

/**
 * Post task for delayed execution on main thread
 */
public fun View.postDelayed(timeout: Number, inline action: (view: View) -> Unit): View {
    postDelayed({
        action(this)
    }, timeout.toLong())
    return this
}

/**
 * Find view in the tree by id
 */
public inline fun View.findView<reified T: View?>(id: Int): T {
    return findViewById(id) as T
}
