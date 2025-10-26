package com.sameershelar.toodo.data.local.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.sameershelar.toodo.data.local.room.dao.ToodoDao
import com.sameershelar.toodo.data.local.room.entities.Toodo

@Database(
    entities = [Toodo::class],
    version = 1,
)
// The @Database class must be annotated with @ConstructedBy since the source is targeting non-Android platforms.
@ConstructedBy(AppDatabaseConstructor::class)
abstract class ToodoDatabase : RoomDatabase() {
    abstract fun toodoDao(): ToodoDao
}

// The Room compiler generates the `actual` implementations.
// compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes") in build.gradle.kts suppresses the warning.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<ToodoDatabase> {
    override fun initialize(): ToodoDatabase
}
