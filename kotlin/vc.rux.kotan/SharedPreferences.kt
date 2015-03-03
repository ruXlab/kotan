package vc.rux.kotan

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty

/**
 * Shared preferences helper
 * (obsolete way)
 */

public fun Context.loadPref<T>(key: String, default: T): T {
    val sp = getSharedPreferences(javaClass.getCanonicalName(), 0)
    return when (default) {
        is Int -> sp.getInt(key, default)
        is Boolean -> sp.getBoolean(key, default)
        is String -> sp.getString(key, default)
        is Float -> sp.getFloat(key, default)
        is Long -> sp.getLong(key, default)
        else -> default
    } as T
}

public fun Context.savePref<T>(key: String, value: T) {
    val sp = getSharedPreferences(javaClass.getCanonicalName(), 0).edit()
    when (value) {
        is Int -> sp.putInt(key, value)
        is Boolean -> sp.putBoolean(key, value)
        is String -> sp.putString(key, value)
        is Float -> sp.putFloat(key, value)
        is Long -> sp.putLong(key, value)
        else -> return
    }
    sp.commit()
}

public fun SharedPreferences.edit(noinline action: (editor: SharedPreferences.Editor) -> Unit) {
    val sp = edit()
    action(sp)
    sp.commit()
}


