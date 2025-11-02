package com.sameershelar.toodo.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getToodoDatabase(
    context: Context,
): ToodoDatabase {
    val dbFile = context.getDatabasePath("toodo.db")
    return Room.databaseBuilder<ToodoDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}