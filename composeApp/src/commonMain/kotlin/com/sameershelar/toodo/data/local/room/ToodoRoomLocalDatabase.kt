package com.sameershelar.toodo.data.local.room

import com.sameershelar.toodo.data.local.room.entities.toDomain
import com.sameershelar.toodo.data.local.room.entities.toEntity
import com.sameershelar.toodo.domain.data.local.ToodoLocalDatabase
import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ToodoRoomLocalDatabase(
    toodoDatabase: ToodoDatabase
) : ToodoLocalDatabase {

    private val toodoDao = toodoDatabase.toodoDao()

    override suspend fun cacheToodos(toodos: List<Toodo>) = coroutineScope {
        toodos.map {
            it.toEntity(isSynced = true)
        }.forEach {
            launch {
                toodoDao.upsertToodo(it)
            }
        }
    }

    override fun getAllToodos(): Flow<List<Toodo>> {
        return toodoDao.getAllToodos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToodo(toodo: Toodo) {
        toodoDao.upsertToodo(toodo.toEntity())
    }

    override suspend fun updateToodo(toodo: Toodo) {
        toodoDao.updateToodo(toodo.toEntity())
    }

    override suspend fun deleteToodo(toodo: Toodo) {
        toodoDao.deleteToodo(toodo.toEntity())
    }

    override suspend fun softDeleteToodo(toodo: Toodo) {
        toodoDao.updateToodo(
            toodo.toEntity(
                isSynced = false,
                isDeleted = true
            )
        )
    }

    override suspend fun softRestoreToodo(toodo: Toodo) {
        toodoDao.updateToodo(
            toodo.toEntity(
                isSynced = false,
                isDeleted = false
            )
        )
    }

    override suspend fun synced(toodo: Toodo) {
        toodoDao.updateToodo(
            toodo.toEntity(isSynced = true)
        )
    }
}