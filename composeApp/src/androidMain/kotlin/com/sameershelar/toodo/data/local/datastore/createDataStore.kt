package com.sameershelar.toodo.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStore(context: Context): DataStore<Preferences> = createCommonDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)