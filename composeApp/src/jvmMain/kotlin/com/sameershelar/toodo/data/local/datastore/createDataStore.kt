package com.sameershelar.toodo.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.io.File

fun createDataStore(): DataStore<Preferences> = createCommonDataStore(
    producePath = {
        val file = File(System.getProperty("java.io.tmpdir"), dataStoreFileName)
        file.absolutePath
    }
)