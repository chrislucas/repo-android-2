package com.br.words.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.br.words.datalayer.entities.WordEntity
import com.br.words.datalayer.repositories.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<WordEntity>> = repository.allWords.asLiveData()

    fun insert(wordEntity: WordEntity) = viewModelScope.launch {
        repository.insert(wordEntity)
    }
}