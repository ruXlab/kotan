package vc.rux.kotan

import android.database.Cursor


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
