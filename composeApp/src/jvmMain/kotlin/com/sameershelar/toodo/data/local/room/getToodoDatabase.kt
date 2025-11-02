package com.sameershelar.toodo.data.local.room

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getToodoDatabase(): ToodoDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "toodo.db")
    println("Database path: ${dbFile.absolutePath}")
    return Room.databaseBuilder<ToodoDatabase>(
        name = dbFile.absolutePath,
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}