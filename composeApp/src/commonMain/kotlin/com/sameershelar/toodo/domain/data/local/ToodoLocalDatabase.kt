package com.sameershelar.toodo.domain.data.local

import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.flow.Flow

interface ToodoLocalDatabase {

    /**
     * Cache a list of Toodos to the local database.
     * Should only be used for caching fetched toodos from remote server.
     */
    suspend fun cacheToodos(toodos: List<Toodo>)

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
     * Soft delete a Toodo from the local database.
     */
    suspend fun softDeleteToodo(toodo: Toodo)

    /**
     * Soft restore a Toodo from the local database.
     */
    suspend fun softRestoreToodo(toodo: Toodo)

    /**
     * Set a Toodo as synced in the local database.
     */
    suspend fun synced(toodo: Toodo)
}