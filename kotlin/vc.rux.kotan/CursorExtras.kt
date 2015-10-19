package vc.rux.kotan

import android.database.Cursor
import java.util.*


fun Cursor.getString(columnName: String) =
    getString(getColumnIndexOrThrow(columnName))

fun Cursor.getInt(columnName: String) =
    getInt(getColumnIndexOrThrow(columnName))

fun Cursor.getDouble(columnName: String) =
    getDouble(getColumnIndexOrThrow(columnName))

fun Cursor.getFloat(columnName: String) =
    getFloat(getColumnIndexOrThrow(columnName))

fun Cursor.getLong(columnName: String) =
    getLong(getColumnIndexOrThrow(columnName))

fun Cursor.getShort(columnName: String) =
    getShort(getColumnIndexOrThrow(columnName))

fun Cursor.getBlob(columnName: String) =
    getBlob(getColumnIndexOrThrow(columnName))

/**
 * Pass remaining cursor entities via mapper function
 */
fun Cursor.mapFromCurrent<T>(f: (record: Cursor) -> T): List<T> {
    val result = ArrayList<T>()
    do {
        result.add(f(this))
    } while (moveToNext())
    return result
}

/**
 * Resets cursor to the start and map each entry
 */
fun Cursor.mapAll<T>(f: (record: Cursor) -> T): List<T> =
    if (moveToFirst()) mapFromCurrent(f)
    else ArrayList<T>(0)

