package com.sameershelar.toodo.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sameershelar.toodo.data.local.room.entities.Toodo
import kotlinx.coroutines.flow.Flow

@Dao
interface ToodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertToodo(toodo: Toodo)

    @Update
    suspend fun updateToodo(toodo: Toodo)

    @Delete
    suspend fun deleteToodo(toodo: Toodo)

    @Query("SELECT * FROM toodo")
    fun getAllToodos(): Flow<List<Toodo>>
}