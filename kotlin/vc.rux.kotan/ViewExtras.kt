package vc.rux.kotan

import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View

/**
 * Set handler for click
 */
public fun View.onClick(clickHandler: () -> Unit): View {
    setOnClickListener() {
        clickHandler()
    }
    return this
}

/**
 * Set handler for long click
 */
public fun View.onLongClick(clickHandler: () -> Boolean): View {
    setOnLongClickListener() {
        clickHandler()
    }
    return this
}

/**
 * Set handler for touch
 */
public fun View.onTouchListener(touchHandler: (event: MotionEvent) -> Boolean): View {
    setOnTouchListener() { v: View, event: MotionEvent ->
        touchHandler(event)
    }
    return this
}


/**
 * Set handler for motion
 */
public fun View.onGenericMotionListener(motionHandler: (event: MotionEvent) -> Boolean): View {
    setOnGenericMotionListener() { view: View, event: MotionEvent ->
        motionHandler(event)
    }
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
 * Set/get visibility state of view
 */
public inline val View.isVisible: Boolean
    get() = getVisibility() == View.VISIBLE

/**
 * Post task for delayed execution on main thread
 */
public fun View.postDelayed(timeout: Number, action: (view: View) -> Unit): View {
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
