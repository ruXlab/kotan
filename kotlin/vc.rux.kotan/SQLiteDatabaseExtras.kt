package vc.rux.kotan

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

fun <T> SQLiteDatabase.transaction(f: (SQLiteDatabase) -> T): T {
    beginTransaction()
    var result: T
    try {
        result = f(this)
        setTransactionSuccessful()
    } finally {
        endTransaction()
    }
    return result
}

fun SQLiteDatabase.queryCursor(
        table: String? = null,
        columns: Array<String>? = null,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
        groupBy: String? = null,
        having: String? = null,
        orderBy: String? = null,
        limit: String? = null
) = query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)

fun <T> SQLiteDatabase.queryAndMap(
        table: String? = null,
        columns: Array<String>? = null,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
        groupBy: String? = null,
        having: String? = null,
        orderBy: String? = null,
        limit: String? = null,
        mapper: (c: Cursor) -> T
) = queryCursor(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
        .mapAllAndClose(mapper)

fun <T> SQLiteDatabase.queryFirstAndMap(
        table: String? = null,
        columns: Array<String>? = null,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
        groupBy: String? = null,
        having: String? = null,
        orderBy: String? = null,
        limit: String? = null,
        default: T,
        mapper: (c: Cursor) -> T
) = queryCursor(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
    .mapFirstRowAndClose(default, mapper)


