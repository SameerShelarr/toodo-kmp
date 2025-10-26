package com.sameershelar.toodo.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sameershelar.toodo.domain.data.local.ToodoLocalKeyValueStorage
import com.sameershelar.toodo.domain.data.local.ToodoLocalKeyValueStorage.Companion.ACCESS_TOKEN_KEY
import com.sameershelar.toodo.domain.data.local.ToodoLocalKeyValueStorage.Companion.REFRESH_TOKEN_KEY
import kotlinx.coroutines.flow.first

class ToodoDataStoreLocalKeyValueStorage(
    private val dataStore: DataStore<Preferences>,
) : ToodoLocalKeyValueStorage {

    private val accessTokenKey = stringPreferencesKey(ACCESS_TOKEN_KEY)
    private val refreshTokenKey = stringPreferencesKey(REFRESH_TOKEN_KEY)

    override suspend fun saveTokenPair(accessToken: String, refreshToken: String) {
        dataStore.edit {
            it[accessTokenKey] = accessToken
            it[refreshTokenKey] = refreshToken
        }
    }

    override suspend fun getAccessToken(): String? {
        val accessToken = try {
            dataStore.data.first()[accessTokenKey]
        } catch (_: NoSuchElementException) {
            null
        }
        return accessToken
    }

    override suspend fun getRefreshToken(): String? {
        val refreshToken = try {
            dataStore.data.first()[refreshTokenKey]
        } catch (_: NoSuchElementException) {
            null
        }
        return refreshToken
    }

    override suspend fun clearTokens() {
        dataStore.edit {
            it.remove(accessTokenKey)
            it.remove(refreshTokenKey)
        }
    }
}