package com.sameershelar.toodo.data.remote.ktor

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun getKtorHttpEngine(): HttpClientEngine {
    return Darwin.create()
}