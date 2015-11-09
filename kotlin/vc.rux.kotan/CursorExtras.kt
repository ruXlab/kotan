package vc.rux.kotan

import android.database.Cursor
import java.util.*


fun Cursor.getString(columnName: String) =
    getString(getColumnIndexOrThrow(columnName))

fun Cursor.getString(columnName: String, default: String?) =
    try { getString(columnName) } catch(e: Exception) { default }

fun Cursor.getInt(columnName: String) =
    getInt(getColumnIndexOrThrow(columnName))

fun Cursor.getInt(columnName: String, default: Int?) =
    try { getInt(columnName) } catch(e: Exception) { default }

fun Cursor.getDouble(columnName: String) =
    getDouble(getColumnIndexOrThrow(columnName))

fun Cursor.getDouble(columnName: String, default: Double?) =
    try { getInt(columnName) } catch(e: Exception) { default }

fun Cursor.getFloat(columnName: String) =
    getFloat(getColumnIndexOrThrow(columnName))

fun Cursor.getFloat(columnName: String, default: Float?) =
    try { getFloat(columnName) } catch(e: Exception) { default }

fun Cursor.getLong(columnName: String) =
    getLong(getColumnIndexOrThrow(columnName))

fun Cursor.getLong(columnName: String, default: Long?) =
    try { getLong(columnName) } catch(e: Exception) { default }

fun Cursor.getShort(columnName: String) =
    getShort(getColumnIndexOrThrow(columnName))

fun Cursor.getShort(columnName: String, default: Short?) =
    try { getShort(columnName) } catch(e: Exception) { default }

fun Cursor.getBlob(columnName: String) =
    getBlob(getColumnIndexOrThrow(columnName))

fun Cursor.getShort(columnName: String, default: ByteArray?) =
    try { getBlob(columnName) } catch(e: Exception) { default }

/**
 * Pass remaining cursor entities via mapper function
 */
fun <T> Cursor.mapFromCurrent(f: (record: Cursor) -> T): List<T> {
    val result = ArrayList<T>()
    do {
        result.add(f(this))
    } while (moveToNext())
    return result
}

/**
 * Resets cursor to the start and map each entry
 */
fun <T> Cursor.mapAll(f: (record: Cursor) -> T): List<T> =
    if (moveToFirst()) mapFromCurrent(f)
    else ArrayList<T>(0)

/**
 *
 * Resets cursor to the start and map each entry and close it afterwards
 */
fun <T> Cursor.mapAllAndClose(f: (record: Cursor) -> T): List<T> {
    val result = mapAll(f)
    close()
    return result
}

fun <T> Cursor.mapFirstRowAndClose(default: T, mapper: (record: Cursor) -> T): T {
    var result: T = default
    try {
        if (moveToFirst()) result = mapper(this)
    } finally {
        close()
    }
    return result
}

fun Cursor.mapFirstIntAndClose(default: Int? = null) =
    mapFirstRowAndClose(default) { it.getInt(0) }

