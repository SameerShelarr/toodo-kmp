package com.sameershelar.toodo.di

import com.sameershelar.toodo.data.ToodoRepository
import com.sameershelar.toodo.data.local.datastore.ToodoDataStoreLocalKeyValueStorage
import com.sameershelar.toodo.data.local.room.ToodoRoomLocalDatabase
import com.sameershelar.toodo.data.remote.ktor.ToodoKtorClientFactory
import com.sameershelar.toodo.data.remote.ktor.ToodoKtorRemoteApi
import com.sameershelar.toodo.domain.data.local.ToodoLocalDatabase
import com.sameershelar.toodo.domain.data.local.ToodoLocalKeyValueStorage
import com.sameershelar.toodo.domain.data.remote.ToodoRemoteApi
import com.sameershelar.toodo.domain.models.Toodo
import com.sameershelar.toodo.presentation.homescreen.viewmodel.HomeViewModel
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.module.Module
import org.koin.dsl.module
import com.sameershelar.toodo.domain.data.ToodoRepository as ToodoRepositoryInterface

expect val platformSpecificModule: Module

val sharedModule = module {
    single<ToodoRepositoryInterface> {
        ToodoRepository(get(), get(), get())
    }

    single<ToodoLocalKeyValueStorage> {
        ToodoDataStoreLocalKeyValueStorage(get())
    }

    single<ToodoLocalDatabase> {
        ToodoRoomLocalDatabase(get())
    }

    single<HomeViewModel> {
        HomeViewModel(get())
    }

    single<HttpClient> {
        ToodoKtorClientFactory().create()
    }

    single<ToodoRemoteApi> {
        ToodoKtorRemoteApi(get())
    }
}

/**
 * This module provides mock implementations for previewing Compose UI components.
 * It should only be used in the context of Compose Previews and not in production code.
 */
val composePreviewModule = module {
    single<ToodoRepositoryInterface> {
        object : ToodoRepositoryInterface {
            override suspend fun fetchAndSaveAllToodos() { /* no-op */
            }

            override fun getAllToodos(): Flow<List<Toodo>> = flowOf(
                listOf(
                    Toodo(
                        id = "1",
                        title = "Sample Toodo 1",
                        isCompleted = false,
                        createdAt = "",
                        color = ""
                    ),
                    Toodo(
                        id = "2",
                        title = "Sample Toodo 2",
                        isCompleted = false,
                        createdAt = "",
                        color = ""
                    ),
                )
            )

            override suspend fun addToodo(toodo: Toodo) { /* no-op */
            }

            override suspend fun updateToodo(toodo: Toodo) { /* no-op */
            }

            override suspend fun deleteToodo(toodo: Toodo) { /* no-op */
            }

            override suspend fun saveAccessToken(token: String) { /* no-op */
            }

            override suspend fun getAccessToken(): String? = null
            override suspend fun clearAccessToken() { /* no-op */
            }
        }
    }

    single<HomeViewModel> {
        HomeViewModel(get())
    }
}