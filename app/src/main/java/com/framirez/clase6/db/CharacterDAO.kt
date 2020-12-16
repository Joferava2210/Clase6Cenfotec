package com.framirez.clase6.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {

    @Insert
    suspend fun insert(character: CharacterEntity)

    @Query("SELECT * FROM characterentity")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

}