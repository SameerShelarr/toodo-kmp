package com.sameershelar.toodo.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Toodo(
    @PrimaryKey
    val id: String,
    val title: String,
    val isCompleted: Boolean,
    val createdAt: String,
    val color: String,
)

fun Toodo.toDomain() = com.sameershelar.toodo.domain.models.Toodo(
    id = id,
    title = title,
    isCompleted = isCompleted,
    createdAt = createdAt,
    color = color,
)

fun com.sameershelar.toodo.domain.models.Toodo.toEntity() = Toodo(
    id = id,
    title = title,
    isCompleted = isCompleted,
    createdAt = createdAt,
    color = color,
)