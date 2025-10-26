package com.sameershelar.toodo.data.remote.ktor

import com.sameershelar.toodo.data.remote.dto.ToodoDTO
import com.sameershelar.toodo.data.remote.dto.toDomainModel
import com.sameershelar.toodo.domain.data.remote.ToodoRemoteApi
import com.sameershelar.toodo.domain.models.TokenPair
import com.sameershelar.toodo.domain.models.Toodo
import com.sameershelar.toodo.domain.models.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header

class ToodoKtorRemoteApi(
    private val client: HttpClient,
) : ToodoRemoteApi {
    override suspend fun fetchAllToodos(): List<Toodo> {
        val response = client.get("/todos") {
            header(
                key = "Authorization",
                value = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2OGUxMzI4OWNkNDk0YjJjYmFlMTFjOTY" +
                        "iLCJ0eXBlIjoiYWNjZXNzIiwiaWF0IjoxNzYxNDcwMDk4LCJleHAiOjE3NjE0NzA5OTh9" +
                        ".48qa5xJ2sBdMw2uyfVUWeUFj0wIES8mJ5PmxPlphLgM"
            )
        }
        when (response.status.value) {
            in 200..299 -> {
                val toodos: List<ToodoDTO> = response.body<List<ToodoDTO>>()
                return toodos.map { it.toDomainModel() }
            }

            else -> {
                return emptyList()
            }
        }
    }

    override suspend fun addToodo(toodo: Toodo) {
        TODO("Not yet implemented")
    }

    override suspend fun updateToodo(toodo: Toodo) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteToodo(toodoId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(
        email: String,
        password: String
    ): User {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): TokenPair {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): TokenPair {
        TODO("Not yet implemented")
    }

}