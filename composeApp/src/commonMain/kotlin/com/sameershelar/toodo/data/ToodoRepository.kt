package com.sameershelar.toodo.data

import com.sameershelar.toodo.domain.data.ToodoRepository
import com.sameershelar.toodo.domain.data.local.ToodoLocalDatabase
import com.sameershelar.toodo.domain.data.local.ToodoLocalKeyValueStorage
import com.sameershelar.toodo.domain.data.remote.ToodoRemoteApi
import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToodoRepository(
    private val localKeyValueStorage: ToodoLocalKeyValueStorage,
    private val localDatabase: ToodoLocalDatabase,
    private val remoteApi: ToodoRemoteApi,
) : ToodoRepository {
    override suspend fun fetchAndSaveAllToodos() = withContext(Dispatchers.IO) {
        val todos = remoteApi.fetchAllToodos()
        todos.forEach { toodo ->
            launch {
                localDatabase.addToodo(toodo)
            }
        }
    }

    override fun getAllToodos(): Flow<List<Toodo>> {
        return localDatabase.getAllToodos()
    }

    override suspend fun addToodo(toodo: Toodo) {
        localDatabase.addToodo(toodo)
    }

    override suspend fun updateToodo(toodo: Toodo) {
        localDatabase.updateToodo(toodo)
    }

    override suspend fun deleteToodo(toodo: Toodo) {
        localDatabase.deleteToodo(toodo)
    }

    override suspend fun saveAccessToken(token: String) {
        localKeyValueStorage.saveTokenPair(token, "")
    }

    override suspend fun getAccessToken(): String? {
        return localKeyValueStorage.getAccessToken()
    }

    override suspend fun clearAccessToken() {
        localKeyValueStorage.clearTokens()
    }
}