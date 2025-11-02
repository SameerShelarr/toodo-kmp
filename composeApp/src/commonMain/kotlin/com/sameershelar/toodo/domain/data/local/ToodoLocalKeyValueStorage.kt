package com.sameershelar.toodo.domain.data.local

interface ToodoLocalKeyValueStorage {

    companion object {
        const val ACCESS_TOKEN_KEY = "access_token"
        const val REFRESH_TOKEN_KEY = "refresh_token"
    }

    /**
     * Save the access and refresh tokens in local key value storage.
     */
    suspend fun saveTokenPair(accessToken: String, refreshToken: String)

    /**
     * Retrieve the access token from local key value storage.
     */
    suspend fun getAccessToken(): String?

    /**
     * Retrieve the refresh token from local key value storage.
     */
    suspend fun getRefreshToken(): String?

    /**
     * Clear the stored tokens from local key value storage.
     */
    suspend fun clearTokens()
}