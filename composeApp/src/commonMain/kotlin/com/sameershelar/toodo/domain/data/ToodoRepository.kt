package com.sameershelar.toodo.domain.data

import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.flow.Flow

interface ToodoRepository {

    /**
     * Fetch all Toodos from the remote server.
     */
    suspend fun fetchAndSaveAllToodos()

    /**
     * Get all Toodos from the local database.
     */
    fun getAllToodos(): Flow<List<Toodo>>

    /**
     * Add a new Toodo to the local database.
     */
    suspend fun addToodo(toodo: Toodo)

    /**
     * Update an existing Toodo in the local database.
     */
    suspend fun updateToodo(toodo: Toodo)

    /**
     * Delete a Toodo from the local database.
     */
    suspend fun deleteToodo(toodo: Toodo)

    /**
     * Save the access token in local storage.
     */
    suspend fun saveAccessToken(token: String)

    /**
     * Retrieve the access token from local storage.
     */
    suspend fun getAccessToken(): String?

    /**
     * Clear the access token from local storage.
     */
    suspend fun clearAccessToken()
}