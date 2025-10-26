package com.sameershelar.toodo.data.local.room

import com.sameershelar.toodo.data.local.room.entities.toDomain
import com.sameershelar.toodo.data.local.room.entities.toEntity
import com.sameershelar.toodo.domain.data.local.ToodoLocalDatabase
import com.sameershelar.toodo.domain.models.Toodo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToodoRoomLocalDatabase(
    toodoDatabase: ToodoDatabase
) : ToodoLocalDatabase {

    private val toodoDao = toodoDatabase.toodoDao()

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

}