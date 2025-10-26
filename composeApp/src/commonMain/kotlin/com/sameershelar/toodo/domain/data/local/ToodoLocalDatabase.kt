package com.sameershelar.toodo.domain.data.local

import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.flow.Flow

interface ToodoLocalDatabase {
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
}