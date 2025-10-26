package com.sameershelar.toodo.domain.models

data class Toodo(
    val id: String,
    val title: String,
    val isCompleted: Boolean,
    val createdAt: String,
    val color: String,
)