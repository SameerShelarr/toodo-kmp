package com.sameershelar.toodo.domain.data.remote

import com.sameershelar.toodo.domain.models.TokenPair
import com.sameershelar.toodo.domain.models.Toodo
import com.sameershelar.toodo.domain.models.User

interface ToodoRemoteApi {

    /**
     * Fetch all Toodos from the remote server.
     */
    suspend fun fetchAllToodos(): List<Toodo>

    /**
     * Add a new Toodo to the remote server.
     */
    suspend fun addToodo(toodo: Toodo)

    /**
     * Update an existing Toodo in the remote server.
     */
    suspend fun updateToodo(toodo: Toodo): Toodo

    /**
     * Delete a Toodo from the remote server.
     */
    suspend fun deleteToodo(toodoId: String)

    /**
     * Registers a new user with the provided email and password.
     */
    suspend fun registerUser(email: String, password: String): User

    /**
     * Logs in a user with the provided email and password.
     */
    suspend fun loginUser(email: String, password: String): TokenPair

    /**
     * Refreshes the authentication token.
     */
    suspend fun refreshToken(): TokenPair
}