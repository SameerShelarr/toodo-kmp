package com.sameershelar.toodo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform