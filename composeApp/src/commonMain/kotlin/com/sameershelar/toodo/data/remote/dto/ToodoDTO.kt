package com.sameershelar.toodo.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToodoDTO(
    val id: String,
    val title: String,
    @SerialName("complete")
    val isCompleted: Boolean,
    val createdAt: String,
    val color: String,
)

fun ToodoDTO.toDomainModel(): com.sameershelar.toodo.domain.models.Toodo {
    return com.sameershelar.toodo.domain.models.Toodo(
        id = id,
        title = title,
        isCompleted = isCompleted,
        createdAt = createdAt,
        color = color,
    )
}