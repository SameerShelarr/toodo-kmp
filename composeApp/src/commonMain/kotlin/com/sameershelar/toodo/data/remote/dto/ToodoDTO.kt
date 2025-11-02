package com.sameershelar.toodo.data.remote.dto

import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.serialization.Serializable

@Serializable
data class ToodoDTO(
    val id: String,
    val title: String,
    val isComplete: Boolean,
    val createdAt: String,
    val color: String,
)

fun ToodoDTO.toDomainModel(): Toodo {
    return Toodo(
        id = id,
        title = title,
        isCompleted = isComplete,
        createdAt = createdAt,
        color = color,
    )
}

fun Toodo.toDTO(): ToodoDTO {
    return ToodoDTO(
        id = id,
        title = title,
        isComplete = isCompleted,
        createdAt = createdAt,
        color = color,
    )
}