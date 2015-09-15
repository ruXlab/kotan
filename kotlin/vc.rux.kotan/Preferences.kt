package vc.rux.kotan

import android.content.Context
import android.content.SharedPreferences
import java.sql.Date
import kotlin.properties.ReadWriteProperty
import kotlin.properties.Delegates

/**
 * Wraps SharedPreferences interface to make it more handsome
 */
public open class Preferences (
        val context: Context,
        val prefName: String = context.javaClass.getCanonicalName()
) {
    protected val prefs: SharedPreferences by Delegates.lazy { context.getSharedPreferences(prefName, 0) }

    protected fun edit(cb: (editor: SharedPreferences.Editor) -> Unit): Unit {
        val editor = prefs.edit()
        cb.invoke(editor)
        editor.commit()
    }

    inner abstract class AbstractValue<T>(val defaultValue: T, val name: String?): ReadWriteProperty<Preferences, T> {
        protected fun detectName(propertyMetadata: PropertyMetadata): String
                = name ?: propertyMetadata.name

    }


    inner class BooleanValue(defaultValue: Boolean, name: String? = null): AbstractValue<Boolean>(defaultValue, name) {
        override fun get(thisRef: Preferences, desc: PropertyMetadata): Boolean =
                thisRef.prefs.getBoolean(detectName(desc), defaultValue)


        override fun set(thisRef: Preferences, desc: PropertyMetadata, value: Boolean) =
                thisRef.edit { editor -> editor.putBoolean(detectName(desc), value) }
    }


    inner class IntValue(defaultValue: Int, name: String? = null): AbstractValue<Int>(defaultValue, name) {
        override fun get(thisRef: Preferences, desc: PropertyMetadata): Int =
                thisRef.prefs.getInt(detectName(desc), defaultValue)


        override fun set(thisRef: Preferences, desc: PropertyMetadata, value: Int) =
                thisRef.edit { editor -> editor.putInt(detectName(desc), value) }
    }

    inner class LongValue(defaultValue: Long, name: String? = null): AbstractValue<Long>(defaultValue, name) {
        override fun get(thisRef: Preferences, desc: PropertyMetadata): Long =
                thisRef.prefs.getLong(detectName(desc), defaultValue)


        override fun set(thisRef: Preferences, desc: PropertyMetadata, value: Long) =
                thisRef.edit { editor -> editor.putLong(detectName(desc), value) }
    }

    inner class FloatValue(defaultValue: Float, name: String? = null): AbstractValue<Float>(defaultValue, name) {
        override fun get(thisRef: Preferences, desc: PropertyMetadata): Float =
                thisRef.prefs.getFloat(detectName(desc), defaultValue)


        override fun set(thisRef: Preferences, desc: PropertyMetadata, value: Float) =
                thisRef.edit { editor -> editor.putFloat(detectName(desc), value) }
    }

    inner class StringValue(defaultValue: String, name: String? = null): AbstractValue<String>(defaultValue, name) {
        override fun get(thisRef: Preferences, desc: PropertyMetadata): String =
                thisRef.prefs.getString(detectName(desc), defaultValue)


        override fun set(thisRef: Preferences, desc: PropertyMetadata, value: String) =
                thisRef.edit { editor -> editor.putString(detectName(desc), value) }
    }

    inner class DateValue(defaultValue: Date, name: String? = null): AbstractValue<Date>(defaultValue, name) {
        override fun get(thisRef: Preferences, desc: PropertyMetadata): Date =
                Date(thisRef.prefs.getLong(detectName(desc), defaultValue.getTime()))

        override fun set(thisRef: Preferences, desc: PropertyMetadata, value: Date) =
                thisRef.edit { editor -> editor.putLong(detectName(desc), value.getTime()) }

    }

}