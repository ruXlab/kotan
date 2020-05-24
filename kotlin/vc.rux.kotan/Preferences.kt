package vc.rux.kotan

import android.content.Context
import android.content.SharedPreferences
import java.sql.Date
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Wraps SharedPreferences interface to make it more handsome
 */
open class Preferences(
        val context: Context,
        val prefName: String = context.javaClass.let { it.canonicalName ?: it.simpleName }
) {
    protected val prefs: SharedPreferences by lazy(LazyThreadSafetyMode.NONE) { context.getSharedPreferences(prefName, 0) }

    protected fun edit(cb: (editor: SharedPreferences.Editor) -> Unit): Unit {
        val editor = prefs.edit()
        cb.invoke(editor)
        editor.commit()
    }

    abstract inner class AbstractValue<T>(val defaultValue: T, val name: String?) : ReadWriteProperty<Preferences, T> {
        protected fun detectName(propertyMetadata: KProperty<*>): String = name
                ?: propertyMetadata.name

    }


    inner class BooleanValue(defaultValue: Boolean, name: String? = null) : AbstractValue<Boolean>(defaultValue, name) {
        override fun getValue(thisRef: Preferences, desc: KProperty<*>): Boolean =
                thisRef.prefs.getBoolean(detectName(desc), defaultValue)


        override fun setValue(thisRef: Preferences, desc: KProperty<*>, value: Boolean) =
                thisRef.edit { editor -> editor.putBoolean(detectName(desc), value) }
    }


    inner class IntValue(defaultValue: Int, name: String? = null) : AbstractValue<Int>(defaultValue, name) {
        override fun getValue(thisRef: Preferences, desc: KProperty<*>): Int =
                thisRef.prefs.getInt(detectName(desc), defaultValue)


        override fun setValue(thisRef: Preferences, desc: KProperty<*>, value: Int) =
                thisRef.edit { editor -> editor.putInt(detectName(desc), value) }
    }

    inner class LongValue(defaultValue: Long, name: String? = null) : AbstractValue<Long>(defaultValue, name) {
        override fun getValue(thisRef: Preferences, desc: KProperty<*>): Long =
                thisRef.prefs.getLong(detectName(desc), defaultValue)


        override fun setValue(thisRef: Preferences, desc: KProperty<*>, value: Long) =
                thisRef.edit { editor -> editor.putLong(detectName(desc), value) }
    }

    inner class FloatValue(defaultValue: Float, name: String? = null) : AbstractValue<Float>(defaultValue, name) {
        override fun getValue(thisRef: Preferences, desc: KProperty<*>): Float =
                thisRef.prefs.getFloat(detectName(desc), defaultValue)


        override fun setValue(thisRef: Preferences, desc: KProperty<*>, value: Float) =
                thisRef.edit { editor -> editor.putFloat(detectName(desc), value) }
    }

    inner class StringValue(defaultValue: String, name: String? = null) : AbstractValue<String>(defaultValue, name) {
        override fun getValue(thisRef: Preferences, desc: KProperty<*>): String =
                thisRef.prefs.getString(detectName(desc), defaultValue)


        override fun setValue(thisRef: Preferences, desc: KProperty<*>, value: String) =
                thisRef.edit { editor -> editor.putString(detectName(desc), value) }
    }

    inner class DateValue(defaultValue: Date, name: String? = null) : AbstractValue<Date>(defaultValue, name) {
        override fun getValue(thisRef: Preferences, desc: KProperty<*>): Date =
                Date(thisRef.prefs.getLong(detectName(desc), defaultValue.getTime()))

        override fun setValue(thisRef: Preferences, desc: KProperty<*>, value: Date) =
                thisRef.edit { editor -> editor.putLong(detectName(desc), value.getTime()) }

    }

}