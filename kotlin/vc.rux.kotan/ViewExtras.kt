package vc.rux.kotan

import android.view.MotionEvent
import android.view.View

/**
 * Set handler for click
 */
fun View.onClick(clickHandler: () -> Unit): View {
    setOnClickListener {
        clickHandler()
    }
    return this
}

/**
 * Set handler for long click
 */
fun View.onLongClick(clickHandler: () -> Boolean): View {
    setOnLongClickListener {
        clickHandler()
    }
    return this
}

/**
 * Set handler for touch
 */
fun View.onTouchListener(touchHandler: (event: MotionEvent) -> Boolean): View {
    setOnTouchListener { v: View, event: MotionEvent ->
        touchHandler(event)
    }
    return this
}


/**
 * Set handler for motion
 */
fun View.onGenericMotionListener(motionHandler: (event: MotionEvent) -> Boolean): View {
    setOnGenericMotionListener { view: View, event: MotionEvent ->
        motionHandler(event)
    }
    return this
}


/**
 * Set view visibility state: visible or gone
 */
fun View.setVisibility(isVisible: Boolean): View {
    visibility = if(isVisible) View.VISIBLE else View.GONE
    return this
}

/**
 * Set/get visibility state of view
 */
var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(isVisible) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }

/**
 * Post task for delayed execution on main thread
 */
fun View.postDelayed(timeout: Number, action: (view: View) -> Unit): View {
    postDelayed({
        action(this)
    }, timeout.toLong())
    return this
}

/**
 * Find view in the tree by id
 */
inline fun <reified T: View?> View.findView(id: Int): T {
    return findViewById<T>(id) as T
}
