package com.framirez.clase6.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.framirez.clase6.db.CharacterDatabase
import com.framirez.clase6.db.CharacterEntity
import com.framirez.clase6.repository.CharacterRepository

class FavoriteListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CharacterRepository
    private val database: CharacterDatabase = CharacterDatabase.getDatabase(application)

    init {
        repository = CharacterRepository(database.characterDao())
    }

    fun getAllProducts() : LiveData<List<CharacterEntity>> = repository.allCharacters.asLiveData()
}