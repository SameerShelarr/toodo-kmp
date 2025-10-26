package com.sameershelar.toodo.data.remote.ktor

import com.sameershelar.toodo.domain.util.Constants.BASE_URL
import com.sameershelar.toodo.domain.util.Constants.PORT
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

class ToodoKtorClientFactory {
    fun create(): HttpClient {
        val engine = getKtorHttpEngine()
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                host = BASE_URL
                port = PORT
            }
        }
    }
}
