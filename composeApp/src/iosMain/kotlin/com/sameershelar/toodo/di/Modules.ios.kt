package com.sameershelar.toodo.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sameershelar.toodo.data.local.datastore.createDataStore
import com.sameershelar.toodo.data.local.room.ToodoDatabase
import com.sameershelar.toodo.data.local.room.getToodoDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformSpecificModule: Module
    get() = module {
        single<DataStore<Preferences>> {
            createDataStore()
        }

        single<ToodoDatabase> {
            getToodoDatabase()
        }
    }