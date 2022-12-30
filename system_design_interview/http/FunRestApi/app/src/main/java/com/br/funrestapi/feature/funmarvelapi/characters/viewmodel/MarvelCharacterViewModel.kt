package com.br.funrestapi.feature.funmarvelapi.characters.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.br.funrestapi.BuildConfig
import com.br.funrestapi.feature.funmarvelapi.characters.http.ProviderHttpClient.provideCharacterApi
import com.br.funrestapi.feature.funmarvelapi.characters.repositories.MarvelCharacterRepository
import com.br.funrestapi.utils.models.Operation
import com.br.funrestapi.utils.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MarvelCharacterViewModel : BaseViewModel() {

    private val stateGetCharacters = MutableStateFlow<Operation<Any>>(Operation.idle())

    val observableStateGetCharacters: StateFlow<Operation<Any>> = stateGetCharacters

    private val repository: MarvelCharacterRepository = MarvelCharacterRepository(provideCharacterApi())

    fun getAllCharacter() =
        viewModelScope.launch {
            repository.getCharactersWithoutFilter()
                .catch {
                    if (BuildConfig.DEBUG) {
                        Log.e("VIEW_MODEL_MARVEL_CHAR", "$this")
                    }
                }
                .collect { operation ->
                    stateGetCharacters.emit(operation)
                }
        }
}