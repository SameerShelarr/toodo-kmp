package com.sameershelar.toodo.data.remote.ktor

import com.sameershelar.toodo.data.remote.dto.ToodoDTO
import com.sameershelar.toodo.data.remote.dto.toDTO
import com.sameershelar.toodo.data.remote.dto.toDomainModel
import com.sameershelar.toodo.domain.data.remote.ToodoRemoteApi
import com.sameershelar.toodo.domain.models.TokenPair
import com.sameershelar.toodo.domain.models.Toodo
import com.sameershelar.toodo.domain.models.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ToodoKtorRemoteApi(
    private val client: HttpClient,
) : ToodoRemoteApi {

    val token =
        "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2OGUxMzI4OWNkNDk0YjJjYmFlMTFjOTYiLCJ0eXBlIjoiYWNjZXNzIi" +
                "wiaWF0IjoxNzYyMDk1MzU4LCJleHAiOjE3NjIwOTYyNTh9.Yaqd1ZF-ExkDKaeCu42VLchLf0gWLA8uVn33CWrZTWc"

    override suspend fun fetchAllToodos(): List<Toodo> {
        val response = client.get("/toodos") {
            header(
                key = "Authorization",
                value = "Bearer $token"
            )
        }
        return when (response.status.value) {
            in 200..299 -> {
                val toodos: List<ToodoDTO> = response.body<List<ToodoDTO>>()
                toodos.map { it.toDomainModel() }
            }

            else -> {
                emptyList()
            }
        }
    }

    override suspend fun addToodo(toodo: Toodo) {
        TODO("Not yet implemented")
    }

    override suspend fun updateToodo(toodo: Toodo): Toodo {
        val response = client.post("/toodos") {
            header(
                key = "Authorization",
                value = "Bearer $token"
            )
            header(
                key = "Content-Type",
                value = "application/json"
            )
            setBody(
                toodo.toDTO()
            )
        }
        when (response.status.value) {
            in 200..299 -> {
                return response.body<ToodoDTO>()
                    .toDomainModel()
            }

            else -> {
                throw Exception("Failed to update toodo")
            }
        }
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