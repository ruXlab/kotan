package vc.rux.kotan

import android.view.animation.Animation

class AnimationListenerAdapter: Animation.AnimationListener {
    private var onRepeat = {}
    private var onEnd = {}
    private var onStart = {}

    fun onRepeat(f: () -> Unit) {
        onRepeat = f
    }

    fun onEnd(f: () -> Unit) {
        onEnd = f
    }

    fun onStart(f: () -> Unit) {
        onStart = f
    }

    override fun onAnimationRepeat(animation: Animation) = onRepeat()
    override fun onAnimationEnd(animation: Animation?) = onEnd()
    override fun onAnimationStart(animation: Animation?) = onStart()
}

fun Animation.animationListener(callbacks: AnimationListenerAdapter.() -> Unit) {
    setAnimationListener(AnimationListenerAdapter().apply(callbacks))
}