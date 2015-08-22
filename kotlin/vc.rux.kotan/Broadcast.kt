package vc.rux.kotan

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.io.Serializable

/**
 * Base class for broadcast messages emitter and receivers
 */
abstract class Broadcast<T: Serializable>() {
    fun intent(context: Context, msg: T) =
        Intent(id)
            .setPackage(context.getPackageName())
            .putExtra(keyName, msg)

    fun sendBroadcast(context: Context, data: T) =
            context.sendBroadcast(intent(context, data))

    fun broadcastReceiver(context: Context, cb: (data: T) -> Unit): ExtendedBroadcastReceiver {
        return object : ExtendedBroadcastReceiver(IntentFilter(id)) {
            override fun onReceive(context: Context, intent: Intent) = cb(transform(intent))
        }
    }

    @suppress("UNCHECKED_CAST")
    fun transform(i: Intent): T = i.getSerializableExtra(keyName) as T

    protected  val keyName: String = javaClass.getSimpleName()
    open val id = javaClass.getPackage().getName() + "." + keyName
}

abstract class ExtendedBroadcastReceiver(
        val filter: IntentFilter
): BroadcastReceiver() {
    fun register(context: Context) = context.registerReceiver(this, filter)
    fun unregister(context: Context) =
        try { context.unregisterReceiver(this) }
        catch (ignore: Exception) { /* any better suggestions? */ }
}

