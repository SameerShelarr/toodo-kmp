package com.sameershelar.toodo.data.remote.ktor

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual fun getKtorHttpEngine(): HttpClientEngine {
    return Android.create()
}