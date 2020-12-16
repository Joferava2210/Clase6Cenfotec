package com.framirez.clase6.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.framirez.clase6.db.CharacterDatabase
import com.framirez.clase6.db.CharacterEntity
import com.framirez.clase6.repository.CharacterRepository
import kotlinx.coroutines.launch

class CreateCharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CharacterRepository
    private val database: CharacterDatabase = CharacterDatabase.getDatabase(application)

    init {
        repository = CharacterRepository(database.characterDao())
    }

    fun insertCharacter(characterEntity: CharacterEntity) {
        viewModelScope.launch {
            repository.insert(characterEntity)
        }
    }
}