package com.framirez.clase6.repository

import com.framirez.clase6.db.CharacterDAO
import com.framirez.clase6.db.CharacterEntity
import kotlinx.coroutines.flow.Flow

class CharacterRepository(private val characterDAO: CharacterDAO) {
    suspend fun insert(characterEntity: CharacterEntity) {
        characterDAO.insert(characterEntity)
    }

    val allCharacters : Flow<List<CharacterEntity>> = characterDAO.getAllCharacters()
}